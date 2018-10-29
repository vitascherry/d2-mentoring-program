package com.example.training.exchangeservice.util;

import com.fasterxml.jackson.databind.JavaType;
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

    public T createResponse(HttpResponse httpResponse, Class<T> clazz) throws IOException {
        String body = toString(httpResponse);
        logResponse(httpResponse.getStatusLine().getStatusCode(), body);

        return mappers
                .get(ContentType.getOrDefault(httpResponse.getEntity()).getMimeType())
                .readValue(body, clazz);
    }

    public T createResponse(HttpResponse httpResponse, JavaType type) throws IOException {
        String body = toString(httpResponse);
        logResponse(httpResponse.getStatusLine().getStatusCode(), body);

        return mappers
                .get(ContentType.getOrDefault(httpResponse.getEntity()).getMimeType())
                .readValue(body, type);
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
