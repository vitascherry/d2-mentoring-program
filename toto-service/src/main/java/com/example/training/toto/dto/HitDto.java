package com.example.training.toto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
