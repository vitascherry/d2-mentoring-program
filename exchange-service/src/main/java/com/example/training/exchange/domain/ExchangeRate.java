package com.example.training.exchange.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

@Data
public class ExchangeRate {

    @JsonProperty("r030")
    private int identifier;

    @JsonProperty("txt")
    private String text;

    @JsonProperty
    private BigDecimal rate;

    @JsonProperty("cc")
    private Currency currency;

    @JsonProperty("exchangedate")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate exchangeDate;

    @JsonCreator
    public static Currency fromValue(String value) {
        return Currency.getInstance(value);
    }
}
