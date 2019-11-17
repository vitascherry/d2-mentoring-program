package com.example.training.toto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistributionDto {

    @Min(value = 0)
    private double first;

    @Min(value = 0)
    private double second;

    @Min(value = 0)
    private double draw;
}
