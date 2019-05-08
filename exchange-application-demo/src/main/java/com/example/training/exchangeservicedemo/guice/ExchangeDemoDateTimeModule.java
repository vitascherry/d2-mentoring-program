package com.example.training.exchangeservicedemo.guice;

import com.example.training.common.guice.DateTimeModule;
import com.google.inject.name.Names;

import java.time.format.DateTimeFormatter;

public class ExchangeDemoDateTimeModule extends DateTimeModule {

    @Override
    protected void configure() {
        DateTimeFormatter dateTimeFormatter = createDateTimeFormatter();
        bind(DateTimeFormatter.class).annotatedWith(Names.named("exchangeDemoDateTimeFormatter")).toInstance(dateTimeFormatter);
    }

    @Override
    protected DateTimeFormatter createDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }
}
