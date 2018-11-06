package com.example.training.exchangeservice.guice;

import com.example.training.common.guice.*;
import com.example.training.common.handler.Printer;
import com.example.training.common.handler.Reader;
import com.example.training.common.service.DateTimeService;
import com.example.training.exchangeservice.client.ExchangeRateClient;
import com.example.training.exchangeservice.domain.ExchangeRate;
import com.example.training.exchangeservice.handler.ExchangeHandler;
import com.example.training.exchangeservice.provider.RemoteExchangeRateEntityProvider;
import com.example.training.exchangeservice.repository.ExchangeRateRepository;
import com.example.training.exchangeservice.repository.impl.RemoteExchangeRateRepository;
import com.example.training.exchangeservice.service.ExchangeRateService;
import com.example.training.exchangeservice.service.impl.RemoteExchangeRateService;
import com.example.training.exchangeservice.util.RequestFactory;
import com.example.training.exchangeservice.util.ResponseFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Executor;
import org.apache.http.impl.client.HttpClientBuilder;

import java.text.DecimalFormat;
import java.util.List;

import static com.example.training.exchangeservice.constant.ExchangeConstants.CONNECTION_TIMEOUT_MILLIS;
import static com.example.training.exchangeservice.constant.ExchangeConstants.NBU_API_URL;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static org.apache.http.entity.ContentType.TEXT_XML;

public class ExchangeModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new PrinterModule());
        install(new ReaderModule());
        install(new ObjectMapperModule());
        install(new XmlMapperModule());
        install(new ExchangeDateTimeModule());
        install(new DecimalModule());

        bind(ExchangeRateService.class).to(RemoteExchangeRateService.class);
        bind(ExchangeRateRepository.class).to(RemoteExchangeRateRepository.class);
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
    public RemoteExchangeRateEntityProvider remoteExchangeRateProvider(ExchangeRateClient client) {
        return new RemoteExchangeRateEntityProvider(client);
    }

    @Singleton
    @Provides
    public ExchangeRateClient exchangeRateClientProvider(HttpClient httpClient, RequestFactory requestFactory,
                                                         ResponseFactory<List<ExchangeRate>> responseFactory) {
        return ExchangeRateClient.builder()
                .path(NBU_API_URL)
                .executor(Executor.newInstance(httpClient))
                .requestFactory(requestFactory)
                .responseFactory(responseFactory)
                .build();
    }

    @Singleton
    @Provides
    public HttpClient httpClientProvider() {
        return HttpClientBuilder
                .create()
                .setConnectionTimeToLive(CONNECTION_TIMEOUT_MILLIS, MILLISECONDS)
                .build();
    }

    @Singleton
    @Provides
    public RequestFactory requestFactoryProvider() {
        return new RequestFactory();
    }

    @Singleton
    @Provides
    public ResponseFactory<List<ExchangeRate>> responseFactoryProvider(ObjectMapper jsonMapper, XmlMapper xmlMapper) {
        return ResponseFactory.<List<ExchangeRate>>builder()
                .mapper(APPLICATION_JSON.getMimeType(), jsonMapper)
                .mapper(TEXT_XML.getMimeType(), xmlMapper)
                .build();
    }

    @Singleton
    @Provides
    public RemoteExchangeRateRepository remoteExchangeRateRepositoryProvider(RemoteExchangeRateEntityProvider provider) {
        return new RemoteExchangeRateRepository(provider);
    }
}
