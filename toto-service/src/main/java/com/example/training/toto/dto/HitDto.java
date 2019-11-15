package com.example.training.toto.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
public class HitDto {

    @Min(value = 0)
    private int round;

    @NotNull
    private LocalDate date;

    @Min(value = 0)
    private int game;

    @NotNull
    private PriceDto priceDto;

    @NotNull
    private OutcomeDto outcomeDto;
}
