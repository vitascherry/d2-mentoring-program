package com.example.training.toto.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Hit {

    private int round;

    private LocalDate date;

    private int game;

    private Price price;

    private Outcome outcome;
}
