package com.example.training.toto.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Distribution {

    private final double first;

    private final double second;

    private final double draw;
}
