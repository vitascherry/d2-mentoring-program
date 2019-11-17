package com.example.training.toto.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class Hit {

    private int round;

    private LocalDate date;

    private int game;

    private Price price;

    private Outcome outcome;
}
