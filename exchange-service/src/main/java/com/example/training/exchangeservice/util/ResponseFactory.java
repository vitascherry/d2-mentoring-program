package com.example.training.exchangeservice.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Singular;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

@Log4j2
@Builder
public class ResponseFactory<T> {

    @Singular("mapper")
    private final Map<String, ObjectMapper> mappers;

    public T createResponse(HttpResponse httpResponse, TypeReference<T> type) throws IOException {
        String body = toString(httpResponse);
        logResponse(httpResponse.getStatusLine().getStatusCode(), body);

        final String mimeType = ContentType.getOrDefault(httpResponse.getEntity()).getMimeType();
        ObjectMapper mapper = mappers.get(mimeType);
        if (mapper == null) {
            throw new IllegalArgumentException(String.format("Not found ObjectMapper for mimeType %s", mimeType));
        }

        return mapper.readValue(body, type);
    }

    private String toString(HttpResponse httpResponse) throws IOException {
        return EntityUtils.toString(httpResponse.getEntity());
    }

    private void logResponse(int statusCode, String body) {
        if (body != null) {
            log.debug("Response: statusCode = {}, body = {}", statusCode, body);
        } else {
            log.debug("Response: statusCode = {}", statusCode);
        }
    }
}
