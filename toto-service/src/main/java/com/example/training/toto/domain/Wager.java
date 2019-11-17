package com.example.training.toto.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Wager {

    private Round round;

    private OutcomeSet outcomes;
}
