package com.example.training.graphy.exception;

import com.example.training.graphy.key.Scope;
import lombok.Getter;

@Getter
public class UnsatisfiedScopeException extends Exception {

    private static final String MESSAGE_FORMAT = "Not allowed scope '%s' here";

    private final Scope scope;

    public UnsatisfiedScopeException(Scope scope) {
        super(String.format(MESSAGE_FORMAT, scope));
        this.scope = scope;
    }
}
