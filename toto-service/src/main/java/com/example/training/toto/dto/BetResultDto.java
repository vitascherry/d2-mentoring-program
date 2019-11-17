package com.example.training.toto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BetResultDto {

    @NotNull
    private List<HitDto> hitDtos;

    @NotNull
    private PriceDto priceDto;
}
