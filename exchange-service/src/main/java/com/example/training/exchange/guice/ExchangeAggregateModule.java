package com.example.training.exchange.guice;

import com.example.training.common.provider.EntityProvider;
import com.example.training.exchange.domain.ExchangeRate;
import com.example.training.exchange.repository.ExchangeRateRepository;
import com.example.training.exchange.repository.impl.ExchangeRateRepositoryImpl;
import com.example.training.exchange.service.ExchangeRateService;
import com.example.training.exchange.service.impl.ExchangeRateServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.util.Modules;

import java.util.Currency;

public class ExchangeAggregateModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ExchangeFilePropertyProvider());

        // This allows to migrate from mock data to real data gradually, without changing the aggregation module
        install(Modules.override(new ExchangeMockModule()).with(new ExchangeModule()));
    }

    @Singleton
    @Provides
    public ExchangeRateService exchangeRateServiceProvider(ExchangeRateRepository repository) {
        return new ExchangeRateServiceImpl(repository);
    }

    @Singleton
    @Provides
    public ExchangeRateRepository exchangeRateRepositoryProvider(EntityProvider<Currency, ExchangeRate> provider) {
        return new ExchangeRateRepositoryImpl(provider);
    }
}
