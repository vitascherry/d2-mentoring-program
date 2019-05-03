package com.example.training.exchangeservice.guice;

import com.example.training.clientcommon.guice.ClientCommonModule;
import com.example.training.clientcommon.guice.RetryableHttpClientModule;
import com.example.training.clientcommon.util.RequestFactory;
import com.example.training.clientcommon.util.ResponseFactory;
import com.example.training.common.guice.CombinedPropertyProviderModule;
import com.example.training.common.guice.DecimalModule;
import com.example.training.common.guice.EnvironmentPropertyProviderModule;
import com.example.training.common.guice.ObjectMapperModule;
import com.example.training.common.guice.PrinterModule;
import com.example.training.common.guice.ReaderModule;
import com.example.training.common.guice.XmlMapperModule;
import com.example.training.common.handler.Printer;
import com.example.training.common.handler.Reader;
import com.example.training.common.service.DateTimeService;
import com.example.training.common.wrappers.DateTimeWrapper;
import com.example.training.exchangeservice.client.ExchangeRateClient;
import com.example.training.exchangeservice.handler.ExchangeHandler;
import com.example.training.exchangeservice.provider.RemoteExchangeRateEntityProvider;
import com.example.training.exchangeservice.repository.ExchangeRateRepository;
import com.example.training.exchangeservice.repository.impl.RemoteExchangeRateRepository;
import com.example.training.exchangeservice.service.ExchangeRateService;
import com.example.training.exchangeservice.service.impl.RemoteExchangeRateService;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.util.Modules;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Executor;

import java.text.DecimalFormat;

import static com.example.training.exchangeservice.constant.ExchangeConstants.NBU_API_URL;

public class ExchangeModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ExchangeRateService.class).to(RemoteExchangeRateService.class);
        bind(ExchangeRateRepository.class).to(RemoteExchangeRateRepository.class);

        install(new PrinterModule());
        install(new ReaderModule());
        install(new ObjectMapperModule());
        install(new XmlMapperModule());
        install(new ExchangeDateTimeModule());
        install(new DecimalModule());
        install(new EnvironmentPropertyProviderModule());
        install(new CombinedPropertyProviderModule());
        install(Modules.override(new ClientCommonModule()).with(new RetryableHttpClientModule()));
    }

    @Singleton
    @Provides
    public ExchangeHandler exchangeHandlerProvider(Printer printer, Reader reader,
                                                   ExchangeRateService exchangeRateService,
                                                   DateTimeService dateTimeService, DecimalFormat decimalFormatter) {
        return ExchangeHandler.builder()
                .printer(printer)
                .reader(reader)
                .exchangeRateService(exchangeRateService)
                .dateTimeService(dateTimeService)
                .decimalFormatter(decimalFormatter)
                .build();
    }

    @Singleton
    @Provides
    public RemoteExchangeRateService remoteExchangeRateServiceProvider(RemoteExchangeRateRepository repository) {
        return new RemoteExchangeRateService(repository);
    }

    @Singleton
    @Provides
    public RemoteExchangeRateEntityProvider remoteExchangeRateProvider(DateTimeWrapper dateTimeWrapper,
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

    @Singleton
    @Provides
    public RemoteExchangeRateRepository remoteExchangeRateRepositoryProvider(RemoteExchangeRateEntityProvider provider) {
        return new RemoteExchangeRateRepository(provider);
    }
}
