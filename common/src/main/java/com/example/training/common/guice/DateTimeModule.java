package com.example.training.common.guice;

import com.google.inject.AbstractModule;

import java.time.format.DateTimeFormatter;

import static com.example.training.common.constant.DefaultConstants.DEFAULT_DATE_FORMAT;

public class DateTimeModule extends AbstractModule {

    @Override
    protected void configure() {
        DateTimeFormatter dateTimeFormatter = createDateTimeFormatter();
        bind(DateTimeFormatter.class).toInstance(dateTimeFormatter);
    }

    protected DateTimeFormatter createDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
    }
}
