package com.example.training.toto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WagerDto {

    @NotNull
    private RoundDto roundDto;

    @NotNull
    private OutcomeSetDto outcomeSetDto;
}
