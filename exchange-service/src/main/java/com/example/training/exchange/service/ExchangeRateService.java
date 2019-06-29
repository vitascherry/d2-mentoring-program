package com.example.training.exchange.service;

import com.example.training.exchange.exception.MissingExchangeRateException;

import java.math.BigDecimal;
import java.util.Currency;

public interface ExchangeRateService {

    /**
     * Returns current exchange rate for the currency passed
     *
     * @param currency Currency instance to look for exchange rate
     * @return BigDecimal representation of current rate of currency passed comparing to SYSTEM_CURRENCY
     */
    BigDecimal getCurrentRate(Currency currency) throws MissingExchangeRateException;
}
