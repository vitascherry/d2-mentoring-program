package com.example.training.toto.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
public class BetResult {

    private final List<Hit> hits;

    private final Price price;
}
