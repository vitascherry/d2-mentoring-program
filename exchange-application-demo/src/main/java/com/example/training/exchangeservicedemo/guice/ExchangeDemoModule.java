package com.example.training.exchangeservicedemo.guice;

import com.example.training.consolecommon.guice.ConsoleCommonModule;
import com.example.training.consolecommon.handler.Handler;
import com.example.training.consolecommon.handler.Printer;
import com.example.training.consolecommon.handler.Reader;
import com.example.training.exchange.guice.ExchangeAggregateModule;
import com.example.training.exchange.service.ExchangeRateService;
import com.example.training.exchangeservicedemo.handler.ExchangeDemoHandler;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public class ExchangeDemoModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ConsoleCommonModule());
        install(new ExchangeDemoDateTimeModule());
        install(new ExchangeDemoDecimalModule());
        install(new ExchangeAggregateModule());
    }

    @Singleton
    @Provides
    public Handler exchangeHandlerProvider(Printer printer, Reader reader,
                                           ExchangeRateService exchangeRateService,
                                           @Named("exchangeDemoDecimalFormat") DecimalFormat decimalFormat,
                                           @Named("exchangeDemoDateTimeFormatter") DateTimeFormatter dateTimeFormatter) {
        return ExchangeDemoHandler.builder()
                .printer(printer)
                .reader(reader)
                .exchangeRateService(exchangeRateService)
                .dateTimeFormatter(dateTimeFormatter)
                .decimalFormat(decimalFormat)
                .build();
    }
}
