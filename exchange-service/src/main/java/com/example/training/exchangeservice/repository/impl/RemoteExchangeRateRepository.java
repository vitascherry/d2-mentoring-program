package com.example.training.exchangeservice.repository.impl;

import com.example.training.exchangeservice.domain.ExchangeRate;
import com.example.training.exchangeservice.provider.RemoteExchangeRateEntityProvider;
import com.example.training.exchangeservice.repository.ExchangeRateRepository;
import lombok.AllArgsConstructor;

import java.util.Currency;
import java.util.Optional;

@AllArgsConstructor
public class RemoteExchangeRateRepository implements ExchangeRateRepository {

    private RemoteExchangeRateEntityProvider entityProvider;

    @Override
    public Optional<ExchangeRate> getCurrentExchangeRate(Currency currency) {
        return Optional.ofNullable(entityProvider.getEntity(currency));
    }
}
