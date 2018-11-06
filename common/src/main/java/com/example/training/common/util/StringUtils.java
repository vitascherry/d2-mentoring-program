package com.example.training.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.joining;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtils {

    public static <T> String join(String delimiter, T[] values, Function<T, String> mapper) {
        return Arrays.stream(values).map(mapper).collect(joining(delimiter));
    }

    public static <T> String join(String delimiter, List<T> values, Function<T, String> mapper) {
        return values.stream().map(mapper).collect(joining(delimiter));
    }
}
