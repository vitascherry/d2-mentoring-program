package com.example.training.toto.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class Round {

    private final int year;

    private final int week;

    private final int round;

    private final LocalDate date;

    private final int numOfGames14Hits;

    private final Price priceOf14Hits;

    private final int numOfGames13Hits;

    private final Price priceOf13Hits;

    private final int numOfGames12Hits;

    private final Price priceOf12Hits;

    private final int numOfGames11Hits;

    private final Price priceOf11Hits;

    private final int numOfGames10Hits;

    private final Price priceOf10Hits;

    private final OutcomeSet outcomes;
}
