package com.example.training.common.client;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HttpStatusCodeEnhancedRuntimeException extends RuntimeException {

    private final int responseCode;

    public HttpStatusCodeEnhancedRuntimeException(int responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }
}
