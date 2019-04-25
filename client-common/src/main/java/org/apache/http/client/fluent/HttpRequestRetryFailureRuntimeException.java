package org.apache.http.client.fluent;

import lombok.NoArgsConstructor;

@NoArgsConstructor
class HttpRequestRetryFailureRuntimeException extends RuntimeException {

    HttpRequestRetryFailureRuntimeException(String message) {
        super(message);
    }
}
