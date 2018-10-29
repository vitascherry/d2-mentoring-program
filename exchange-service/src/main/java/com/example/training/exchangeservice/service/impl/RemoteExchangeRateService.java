package com.example.training.exchangeservice.service.impl;

import com.example.training.exchangeservice.domain.ExchangeRate;
import com.example.training.exchangeservice.repository.ExchangeRateRepository;
import com.example.training.exchangeservice.service.ExchangeRateService;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;

@AllArgsConstructor
public class RemoteExchangeRateService implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    @Override
    public BigDecimal getCurrentRate(Currency currency) {
        return exchangeRateRepository.getCurrentExchangeRate(currency)
                .map(ExchangeRate::getRate)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Unknown exchange rate for currency '%s'", currency)));
    }
}
