package com.example.training.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FunctionUtils {

    public static <T, R> Function<T, R> extract(final Function<T, R> foo) {
        return foo;
    }

    public static <T, R> Function<T, R> unchecked(final ThrowingFunction<T, R> foo) {
        requireNonNull(foo, "foo must not be null");

        return arg -> {
            try {
                return foo.apply(arg);
            } catch (Exception ex) {
                return ThrowingFunction.sneakyThrow(ex);
            }
        };
    }
}
