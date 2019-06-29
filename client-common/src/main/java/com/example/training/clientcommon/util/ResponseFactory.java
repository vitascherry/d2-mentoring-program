package com.example.training.clientcommon.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Singular;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

@Log4j2
@Builder
public class ResponseFactory {

    @Singular("mapper")
    private final Map<String, ObjectMapper> mappers;

    public <T> T createResponse(HttpResponse httpResponse, Class<T> type) throws IOException {
        HttpEntity httpEntity = httpResponse.getEntity();
        String body = EntityUtils.toString(httpEntity);
        logResponse(httpResponse.getStatusLine().getStatusCode(), body);

        return findMapper(httpEntity).readValue(body, type);
    }

    public <T> T createResponse(HttpResponse httpResponse, TypeReference<T> type) throws IOException {
        HttpEntity httpEntity = httpResponse.getEntity();
        String body = EntityUtils.toString(httpEntity);
        logResponse(httpResponse.getStatusLine().getStatusCode(), body);

        return findMapper(httpEntity).readValue(body, type);
    }

    private ObjectMapper findMapper(HttpEntity httpEntity) {
        final String mimeType = ContentType.getOrDefault(httpEntity).getMimeType();
        ObjectMapper mapper = mappers.get(mimeType);
        if (mapper == null) {
            throw new IllegalArgumentException(String.format("Not found ObjectMapper for mimeType %s", mimeType));
        }
        return mapper;
    }

    private void logResponse(int statusCode, String body) {
        if (body != null) {
            log.debug("Response: statusCode = {}, body = {}", statusCode, body);
        } else {
            log.debug("Response: statusCode = {}", statusCode);
        }
    }
}
