package com.example.training.toto.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static java.util.Arrays.stream;

@AllArgsConstructor
@Getter
public enum Outcome {

    FIRST("1"),
    SECOND("2"),
    DRAW("x");

    private String value;

    @Override
    public String toString() {
        return value;
    }

    public static Outcome fromValue(final String value) {
        return stream(Outcome.values())
                .filter(b -> b.value.equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
