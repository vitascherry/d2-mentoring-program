package org.apache.http.client.fluent;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HttpRequestRetryFailureRuntimeException extends RuntimeException {

    public HttpRequestRetryFailureRuntimeException(String message) {
        super(message);
    }
}
