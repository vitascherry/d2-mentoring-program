package com.example.training.exchangeservice.client;

import com.example.training.common.client.HttpStatusCodeEnhancedRuntimeException;
import com.example.training.exchangeservice.domain.ExchangeRate;
import com.example.training.exchangeservice.domain.RequestEntity;
import com.example.training.exchangeservice.util.RequestFactory;
import com.example.training.exchangeservice.util.ResponseFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Builder;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;

import javax.ws.rs.HttpMethod;
import java.io.IOException;
import java.util.List;

import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;

@Log4j2
@Builder
public class ExchangeRateClient {

    private static final TypeReference<List<ExchangeRate>> EXCHANGE_RATE_TYPE = new TypeReference<List<ExchangeRate>>() {};

    private final String path;
    private final Executor executor;
    private final RequestFactory requestFactory;
    private final ResponseFactory<List<ExchangeRate>> responseFactory;

    @SneakyThrows
    public List<ExchangeRate> getExchangeRates() {
        Request request = requestFactory.createRequest(RequestEntity.builder()
                .httpMethod(HttpMethod.GET)
                .path(path)
                .queryParam("", "json")
                .build());

        HttpResponse httpResponse = executeRequest(request);
        return responseFactory.createResponse(httpResponse, EXCHANGE_RATE_TYPE);
    }

    private HttpResponse executeRequest(Request request) {
        try {
            return executor.execute(request).returnResponse();
        } catch (IOException e) {
            log.error("Error occurred while requesting '{}'. ErrorMessage: ", request.toString(), e);
            throw new HttpStatusCodeEnhancedRuntimeException(SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
