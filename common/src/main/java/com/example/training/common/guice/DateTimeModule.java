package com.example.training.common.guice;

import com.example.training.common.service.DateTimeService;
import com.google.inject.AbstractModule;

import java.time.format.DateTimeFormatter;

import static com.example.training.common.constant.DefaultConstants.DEFAULT_DATE_FORMAT;

public class DateTimeModule extends AbstractModule {

    protected DateTimeFormatter dateTimeFormatter;

    @Override
    protected void configure() {
        dateTimeFormatter = createDateTimeFormatter();
        bind(DateTimeFormatter.class).toInstance(dateTimeFormatter);

        DateTimeService dateTimeService = createDateTimeService();
        bind(DateTimeService.class).toInstance(dateTimeService);
    }

    protected DateTimeFormatter createDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
    }

    protected DateTimeService createDateTimeService() {
        return new DateTimeService(dateTimeFormatter);
    }
}
