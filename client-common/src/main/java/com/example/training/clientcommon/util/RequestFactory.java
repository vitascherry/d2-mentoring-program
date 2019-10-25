package com.example.training.clientcommon.util;

import com.example.training.clientcommon.domain.RequestEntity;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.extern.log4j.Log4j2;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static org.apache.http.HttpHeaders.CONTENT_TYPE;

@Log4j2
@Builder
public class RequestFactory {

    @Singular("supports")
    private final Map<String, Function<String, Request>> supported;

    public Request createRequest(@NonNull RequestEntity requestEntity) {

        final String httpMethod = requestEntity.getHttpMethod();
        final String path = new PathFormatter()
                .withPattern(requestEntity.getPath())
                .withPathParams(requestEntity.getPathParams())
                .withQueryParams(requestEntity.getQueryParams())
                .format();

        Request request = Optional.ofNullable(supported.get(httpMethod))
                .orElseThrow(() -> new IllegalArgumentException(String.format("Unsupported http method %s", httpMethod)))
                .apply(path);

        requestEntity.getHeaderParams().forEach(request::addHeader);

        final String body = requestEntity.getBody();
        final ContentType contentType = requestEntity.getContentType();
        if (body != null) {
            request.addHeader(CONTENT_TYPE, contentType.getMimeType()).bodyString(requestEntity.getBody(), contentType);
        }

        logRequest(httpMethod, path, body);
        return request;
    }

    private void logRequest(String httpMethod, String requestUrl, String body) {
        if (body != null) {
            log.debug("Request: httpMethod = {}, url = {}, body = {}", httpMethod, requestUrl, body);
        } else {
            log.debug("Request: httpMethod = {}, url = {}", httpMethod, requestUrl);
        }
    }
}
