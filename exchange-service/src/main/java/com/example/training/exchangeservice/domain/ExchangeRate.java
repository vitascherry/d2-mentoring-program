package com.example.training.exchangeservice.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static com.example.training.exchangeservice.constant.ExchangeConstants.DATE_FORMAT;

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
    @JsonFormat(pattern = DATE_FORMAT)
    private LocalDate exchangeDate;

    @JsonCreator
    public static Currency fromValue(String value) {
        return Currency.getInstance(value);
    }
}
