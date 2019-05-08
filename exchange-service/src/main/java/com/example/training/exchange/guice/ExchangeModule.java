package com.example.training.exchange.guice;

import com.example.training.clientcommon.guice.ClientAggregateModule;
import com.example.training.clientcommon.guice.RetryableHttpClientModule;
import com.example.training.clientcommon.util.RequestFactory;
import com.example.training.clientcommon.util.ResponseFactory;
import com.example.training.common.guice.ObjectMapperModule;
import com.example.training.common.guice.XmlMapperModule;
import com.example.training.common.provider.EntityProvider;
import com.example.training.common.wrappers.DateTimeWrapper;
import com.example.training.exchange.client.ExchangeRateClient;
import com.example.training.exchange.domain.ExchangeRate;
import com.example.training.exchange.provider.RemoteExchangeRateEntityProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.util.Modules;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Executor;

import java.util.Currency;

import static com.example.training.exchange.constant.ExchangeConstants.NBU_API_URL;

public class ExchangeModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ObjectMapperModule());
        install(new XmlMapperModule());

        // That was made for simplifying the task of injecting the RetryableHttpClient
        install(Modules.override(new ClientAggregateModule()).with(new RetryableHttpClientModule()));
    }

    @Singleton
    @Provides
    public EntityProvider<Currency, ExchangeRate> entityProviderProvider(DateTimeWrapper dateTimeWrapper,
                                                                         ExchangeRateClient client) {
        return new RemoteExchangeRateEntityProvider(dateTimeWrapper, client);
    }

    @Singleton
    @Provides
    public ExchangeRateClient exchangeRateClientProvider(HttpClient httpClient, RequestFactory requestFactory,
                                                         ResponseFactory responseFactory) {
        return ExchangeRateClient.builder()
                .path(NBU_API_URL)
                .executor(Executor.newInstance(httpClient))
                .requestFactory(requestFactory)
                .responseFactory(responseFactory)
                .build();
    }
}
