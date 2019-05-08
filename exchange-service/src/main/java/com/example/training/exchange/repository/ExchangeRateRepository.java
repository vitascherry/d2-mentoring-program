package com.example.training.exchange.repository;

import com.example.training.exchange.domain.ExchangeRate;

import java.util.Currency;
import java.util.Optional;

public interface ExchangeRateRepository {

    Optional<ExchangeRate> getCurrentExchangeRate(Currency currency);
}
