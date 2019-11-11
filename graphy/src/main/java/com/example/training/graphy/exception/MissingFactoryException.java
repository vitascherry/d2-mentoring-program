package com.example.training.graphy.exception;

import com.example.training.graphy.key.Key;
import lombok.Getter;

@Getter
public class MissingFactoryException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "Not found Factory implementation for key '%s'";

    private final Key key;

    public MissingFactoryException(Key key) {
        super(String.format(MESSAGE_FORMAT, key));
        this.key = key;
    }
}
