package com.example.training.toto.dto;

import lombok.Value;

import javax.validation.constraints.Min;

@Value
public class DistributionDto {

    @Min(value = 0)
    private double first;

    @Min(value = 0)
    private double second;

    @Min(value = 0)
    private double draw;
}
