package com.example.training.exchangeservice.provider;

import com.example.training.common.provider.CachedEntityProvider;
import com.example.training.exchangeservice.client.ExchangeRateClient;
import com.example.training.exchangeservice.domain.ExchangeRate;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

import static java.util.stream.Collectors.toMap;

@RequiredArgsConstructor
public class RemoteExchangeRateEntityProvider extends CachedEntityProvider<Currency, ExchangeRate> {

    private final ExchangeRateClient client;
    private LocalDate timestamp;

    @Override
    protected void initCacheIfNeeded() {
        if (!LocalDate.now().equals(timestamp)) {
            initCache();
        }
    }

    @Override
    protected void initCache() {
        List<ExchangeRate> exchangeRates = client.getExchangeRates();
        cache = exchangeRates.stream()
                .collect(toMap(ExchangeRate::getCurrency, x -> x));
        timestamp = exchangeRates.stream()
                .map(ExchangeRate::getExchangeDate)
                .findFirst()
                .orElse(null);
    }
}
