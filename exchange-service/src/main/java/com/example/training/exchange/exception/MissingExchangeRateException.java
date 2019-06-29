package com.example.training.exchange.exception;

import lombok.Getter;

import java.util.Currency;

@Getter
public class MissingExchangeRateException extends Exception {

    private static final String MESSAGE_PLACEHOLDER = "Unknown exchange rate for currency %s";

    private Currency currency;

    public MissingExchangeRateException(Currency currency) {
        super(String.format(MESSAGE_PLACEHOLDER, currency));
        this.currency = currency;
    }
}
