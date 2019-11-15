package com.example.training.toto.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static java.util.Arrays.stream;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Outcome {

    FIRST("1"),
    SECOND("2"),
    DRAW("x");

    private final String value;

    @Override
    public String toString() {
        return value;
    }

    public static Outcome fromValue(String value) {
        return stream(Outcome.values())
                .filter(b -> b.value.equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
