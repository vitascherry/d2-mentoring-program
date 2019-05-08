package com.example.training.totodemo.guice;

import com.example.training.common.guice.DateTimeModule;
import com.google.inject.name.Names;

import java.time.format.DateTimeFormatter;

import static com.example.training.toto.constant.TotoConstants.DATE_FORMAT;

public class TotoDemoDateTimeModule extends DateTimeModule {

    @Override
    protected void configure() {
        DateTimeFormatter dateTimeFormatter = createDateTimeFormatter();
        bind(DateTimeFormatter.class).annotatedWith(Names.named("totoDemoDateTimeFormatter")).toInstance(dateTimeFormatter);
    }

    @Override
    protected DateTimeFormatter createDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(DATE_FORMAT);
    }
}
