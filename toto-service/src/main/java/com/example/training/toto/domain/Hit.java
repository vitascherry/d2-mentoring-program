package com.example.training.toto.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class Hit {

    private final int round;

    private final LocalDate date;

    private final int game;

    private final Price price;

    private final Outcome outcome;
}
