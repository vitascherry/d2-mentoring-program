package com.example.training.toto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceDto {

    @JsonProperty
    @NotNull
    private BigDecimal amount;
    @JsonProperty
    @NotNull
    private Currency currency;
}
