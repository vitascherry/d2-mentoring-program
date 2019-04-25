package com.example.training.clientcommon.util;

import com.example.training.clientcommon.domain.RequestEntity;
import lombok.extern.log4j.Log4j2;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static javax.ws.rs.HttpMethod.*;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;

@Log4j2
public class RequestFactory {

    private static final Map<String, Function<String, Request>> SUPPORTED = new HashMap<>();

    static {
        SUPPORTED.put(POST, Request::Post);
        SUPPORTED.put(GET, Request::Get);
        SUPPORTED.put(PUT, Request::Put);
        SUPPORTED.put(DELETE, Request::Delete);
    }

    public Request createRequest(RequestEntity requestEntity) {
        final StringBuilder url = new StringBuilder(requestEntity.getPath());

        requestEntity.getPathParams().forEach((key, value) -> {
            String pattern = String.format("{%s}", key);
            String replacement = String.format("{%s}", value);
            int start = url.indexOf(pattern);
            int end = start + pattern.length();
            url.replace(start, end, replacement);
        });

        if (!requestEntity.getQueryParams().isEmpty()) {
            url.append('?');
        }
        for (Iterator<Map.Entry<String, String>> it = requestEntity.getQueryParams().entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, String> param = it.next();
            if (param.getKey() != null && !param.getKey().isEmpty()) {
                url.append(param.getKey()).append('=');
            }
            url.append(param.getValue());
            if (it.hasNext()) {
                url.append('&');
            }
        }

        final String httpMethod = requestEntity.getHttpMethod();
        final String path = url.toString();
        Request request = Optional.ofNullable(SUPPORTED.get(httpMethod))
                .orElseThrow(() -> new IllegalArgumentException(String.format("Unsupported http method '%s'", httpMethod)))
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
