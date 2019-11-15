package com.example.training.toto.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Currency;

@Value
@AllArgsConstructor
public class Price {

    private final BigDecimal amount;

    private final Currency currency;
}
