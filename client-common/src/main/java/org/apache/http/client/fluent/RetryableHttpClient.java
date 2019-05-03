package org.apache.http.client.fluent;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.net.SocketException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.util.Objects.requireNonNull;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Log4j2
@AllArgsConstructor
public class RetryableHttpClient implements HttpClient {

    private final HttpClient httpClient;
    private final int maxWaitTimeout;
    private final int retryLimit;

    private <T> T executeWithRetry(HttpRequest request, final HttpHost target, final HttpContext context,
                                   final ResponseHandler<? extends T> responseHandler) throws IOException {
        requireNonNull(request, "request must not be null");

        int numOfRetry = 0;

        while (numOfRetry <= retryLimit) {
            final HttpRequest currentRequest = cloneRequest((HttpUriRequest) request);
            try {
                final int retryAttempt = numOfRetry;
                return CompletableFuture.supplyAsync(() -> {
                    try {
                        T response = executeWithClient(httpClient, currentRequest, target, context, responseHandler);
                        if (retryAttempt > 0) {
                            log.info("Successfully retried request {} after {} attempt(s).",
                                    request.getRequestLine().getUri(), retryAttempt);
                        }
                        return response;
                    } catch (IOException ex) {
                        if (ex instanceof SocketException) {
                            throw new RuntimeException(ex);
                        } else {
                            throw new CompletionException(ex);
                        }
                    }
                }).get(maxWaitTimeout, MILLISECONDS);
            } catch (InterruptedException | TimeoutException | ExecutionException ex) {
                logHttpRequestAttempt(ex, currentRequest, numOfRetry);
                ((HttpUriRequest) currentRequest).abort();
                if (ex instanceof ExecutionException) {
                    Exception wrappedException = (Exception) ex.getCause();
                    if (wrappedException instanceof IOException) {
                        throw (IOException) ex.getCause();
                    }
                }
            }
            numOfRetry++;
        }

        log.error("Failed to numOfRetry http request after {} times.", retryLimit);
        throw new HttpRequestRetryFailureRuntimeException(
                String.format("Failed to numOfRetry http request after %d times.", retryLimit)
        );
    }

    private HttpRequest cloneRequest(HttpUriRequest request) {
        HttpRequest newRequest;
        if (request instanceof InternalEntityEnclosingHttpRequest) {
            newRequest = new InternalEntityEnclosingHttpRequest(request.getMethod(), request.getURI());
            ((InternalEntityEnclosingHttpRequest) newRequest).setEntity(((InternalEntityEnclosingHttpRequest) request).getEntity());
        } else {
            newRequest = new InternalHttpRequest(request.getMethod(), request.getURI());
        }
        newRequest.setHeaders(request.getAllHeaders());
        return newRequest;
    }

    private void logHttpRequestAttempt(Exception ex, HttpRequest request, int retryNum) {
        log.warn("Caught exception: {}. Message: {}.", ex.getClass(), Objects.toString(ex.getMessage(), ""));
        if (retryNum == 0) {
            log.warn("Http request {} failed and will be retried {} times.",
                    request.getRequestLine().getUri(), retryLimit);
        } else {
            log.warn("Retrying http request {}: {} out of {}.",
                    request.getRequestLine().getUri(), retryNum, retryLimit);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T executeWithClient(HttpClient httpClient, HttpRequest request, HttpHost target, HttpContext context,
                                    ResponseHandler<? extends T> responseHandler) throws IOException {
        if (responseHandler == null) {
            return request instanceof HttpUriRequest ? (T) httpClient.execute((HttpUriRequest) request, context)
                    : (T) httpClient.execute(target, request, context);
        } else {
            return request instanceof HttpUriRequest
                    ? httpClient.execute((HttpUriRequest) request, responseHandler, context)
                    : httpClient.execute(target, request, responseHandler, context);
        }
    }

    @Override
    public HttpParams getParams() {
        return httpClient.getParams();
    }

    @Override
    public ClientConnectionManager getConnectionManager() {
        return httpClient.getConnectionManager();
    }

    @Override
    public HttpResponse execute(HttpUriRequest request) throws IOException {
        return executeWithRetry(request, null, null, null);
    }

    @Override
    public HttpResponse execute(HttpHost target, HttpRequest request) throws IOException {
        return executeWithRetry(request, target, null, null);
    }

    @Override
    public HttpResponse execute(HttpUriRequest request, HttpContext context) throws IOException {
        return executeWithRetry(request, null, context, null);
    }

    @Override
    public HttpResponse execute(HttpHost target, HttpRequest request, HttpContext context) throws IOException {
        return executeWithRetry(request, target, context, null);
    }

    @Override
    public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler) throws IOException {
        return executeWithRetry(request, null, null, responseHandler);
    }

    @Override
    public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler) throws IOException {
        return executeWithRetry(request, target, null, responseHandler);
    }

    @Override
    public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException {
        return executeWithRetry(request, null, context, responseHandler);
    }

    @Override
    public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException {
        return executeWithRetry(request, target, context, responseHandler);
    }
}
