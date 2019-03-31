package com.example.training.toto.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static java.util.Arrays.stream;

@Getter
@AllArgsConstructor
public enum Outcome {

    FIRST("1"),
    SECOND("2"),
    DRAW("x");

    private String value;

    @Override
    @JsonValue
    public String toString() {
        return value;
    }

    @JsonCreator
    public static Outcome fromValue(final String value) {
        return stream(Outcome.values())
                .filter(b -> b.value.equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
