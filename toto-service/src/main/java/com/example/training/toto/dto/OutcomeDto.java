package com.example.training.toto.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

import static java.util.Arrays.stream;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Getter
public enum OutcomeDto {

    FIRST("1"),
    SECOND("2"),
    DRAW("x");

    @NotNull
    private String value;

    @Override
    @JsonValue
    public String toString() {
        return value;
    }

    @JsonCreator
    public static OutcomeDto fromValue(String value) {
        return stream(OutcomeDto.values())
                .filter(b -> b.value.equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
