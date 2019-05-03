package com.example.training.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FunctionUtils {

    public static <T, R> Function<T, R> extract(final Function<T, R> foo) {
        return foo;
    }

    public static <T, R> Function<T, R> unchecked(final @NonNull ThrowingFunction<T, R> foo) {
        return arg -> {
            try {
                return foo.apply(arg);
            } catch (Exception ex) {
                return ThrowingFunction.sneakyThrow(ex);
            }
        };
    }
}
