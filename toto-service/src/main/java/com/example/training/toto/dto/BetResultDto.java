package com.example.training.toto.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.List;

@Value
public class BetResultDto {

    @NotNull
    private final List<HitDto> hitDtos;

    @NotNull
    private final PriceDto priceDto;
}
