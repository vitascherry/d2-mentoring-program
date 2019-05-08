package com.example.training.exchange.provider;

import com.example.training.common.provider.CachedEntityProvider;
import com.example.training.common.wrappers.DateTimeWrapper;
import com.example.training.exchange.client.ExchangeRateClient;
import com.example.training.exchange.domain.ExchangeRate;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@RequiredArgsConstructor
public class RemoteExchangeRateEntityProvider extends CachedEntityProvider<Currency, ExchangeRate> {

    private final DateTimeWrapper dateTimeWrapper;
    private final ExchangeRateClient client;
    private LocalDate timestamp;

    @Override
    protected void initCacheIfNeeded() {
        if (!dateTimeWrapper.currentDate().equals(timestamp)) {
            cache = initCache();
        }
    }

    @Override
    protected Map<Currency, ExchangeRate> initCache() {
        List<ExchangeRate> exchangeRates = client.getExchangeRates();

        timestamp = exchangeRates.stream()
                .map(ExchangeRate::getExchangeDate)
                .findFirst()
                .orElse(null);

        return exchangeRates.stream()
                .collect(toMap(ExchangeRate::getCurrency, x -> x));
    }
}
