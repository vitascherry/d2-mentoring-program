package com.example.training.exchange.service.impl;

import com.example.training.exchange.domain.ExchangeRate;
import com.example.training.exchange.repository.ExchangeRateRepository;
import com.example.training.exchange.service.ExchangeRateService;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;

@AllArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    @Override
    public BigDecimal getCurrentRate(final Currency currency) {
        return exchangeRateRepository.getCurrentExchangeRate(currency)
                .map(ExchangeRate::getRate)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Unknown exchange rate for currency %s", currency)));
    }
}
