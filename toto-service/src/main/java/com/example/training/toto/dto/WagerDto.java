package com.example.training.toto.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class WagerDto {

    @NotNull
    private RoundDto roundDto;

    @NotNull
    private OutcomeSetDto outcomeSetDto;
}
