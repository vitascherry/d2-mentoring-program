package com.example.training.toto.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Distribution {

    private double first;

    private double second;

    private double draw;
}
