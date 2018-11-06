package com.example.training.common.util;

@FunctionalInterface
public interface ThrowingFunction<T, R> {

    R apply(T t) throws Exception;

    @SuppressWarnings("unchecked")
    static <T extends Exception, R> R sneakyThrow(Exception t) throws T {
        throw (T) t;
    }
}
