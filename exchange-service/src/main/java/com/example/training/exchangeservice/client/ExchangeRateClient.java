package com.example.training.exchangeservice.client;

import com.example.training.exchangeservice.domain.ExchangeRate;
import com.example.training.exchangeservice.domain.RequestEntity;
import com.example.training.exchangeservice.util.RequestFactory;
import com.example.training.exchangeservice.util.ResponseFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;

import javax.ws.rs.HttpMethod;
import java.io.IOException;
import java.util.List;

import static java.util.Collections.emptyList;

@Log4j2
@Builder
public class ExchangeRateClient {

    private static final TypeReference<List<ExchangeRate>> EXCHANGE_RATE_TYPE = new TypeReference<List<ExchangeRate>>() {};

    private final String path;
    private final Executor executor;
    private final RequestFactory requestFactory;
    private final ResponseFactory<List<ExchangeRate>> responseFactory;

    public List<ExchangeRate> getExchangeRates() {
        Request request = requestFactory.createRequest(RequestEntity.builder()
                .httpMethod(HttpMethod.GET)
                .path(path)
                .queryParam("", "json")
                .build());

        try {
            HttpResponse httpResponse = executor.execute(request).returnResponse();
            return responseFactory.createResponse(httpResponse, EXCHANGE_RATE_TYPE);

        } catch (IOException e) {
            log.error("Error occurred while requesting '{}'. ErrorMessage: ", request.toString(), e);
            return emptyList();
        }
    }
}
