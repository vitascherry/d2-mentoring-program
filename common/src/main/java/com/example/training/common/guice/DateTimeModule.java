package com.example.training.common.guice;

import com.example.training.common.service.DateTimeService;
import com.google.inject.AbstractModule;

import java.time.format.DateTimeFormatter;

import static com.example.training.common.constant.CommonConstants.DEFAULT_DATE_FORMAT;

public class DateTimeModule extends AbstractModule {

    protected DateTimeFormatter dateTimeFormatter;
    protected DateTimeService dateTimeService;

    @Override
    protected void configure() {
        bindDateTimeFormatter();
        bindDateTimeService();
    }

    protected void bindDateTimeFormatter() {
        dateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);

        bind(DateTimeFormatter.class).toInstance(dateTimeFormatter);
    }

    protected void bindDateTimeService() {
        dateTimeService = new DateTimeService(dateTimeFormatter);

        bind(DateTimeService.class).toInstance(dateTimeService);
    }
}
