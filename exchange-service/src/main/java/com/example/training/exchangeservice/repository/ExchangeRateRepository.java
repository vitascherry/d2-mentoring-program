package com.example.training.exchangeservice.repository;

import com.example.training.exchangeservice.domain.ExchangeRate;

import java.util.Currency;
import java.util.List;
import java.util.Optional;

public interface ExchangeRateRepository {

    Optional<ExchangeRate> getCurrentExchangeRate(Currency currency);
}
