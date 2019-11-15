package com.example.training.toto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;

@Value
public class PriceDto {

    @JsonProperty
    @NotNull
    private BigDecimal amount;
    @JsonProperty
    @NotNull
    private Currency currency;
}
