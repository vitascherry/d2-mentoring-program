package com.example.training.exchange.repository.impl;

import com.example.training.common.provider.EntityProvider;
import com.example.training.exchange.domain.ExchangeRate;
import com.example.training.exchange.repository.ExchangeRateRepository;
import lombok.AllArgsConstructor;

import java.util.Currency;
import java.util.Optional;

@AllArgsConstructor
public class ExchangeRateRepositoryImpl implements ExchangeRateRepository {

    private final EntityProvider<Currency, ExchangeRate> entityProvider;

    @Override
    public Optional<ExchangeRate> getCurrentExchangeRate(Currency currency) {
        return Optional.ofNullable(entityProvider.getEntity(currency));
    }
}
