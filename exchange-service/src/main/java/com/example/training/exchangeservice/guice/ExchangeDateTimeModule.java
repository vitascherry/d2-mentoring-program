package com.example.training.exchangeservice.guice;

import com.example.training.common.guice.DateTimeModule;

import java.time.format.DateTimeFormatter;

import static com.example.training.exchangeservice.constant.ExchangeConstants.DATE_FORMAT;

public class ExchangeDateTimeModule extends DateTimeModule {

    @Override
    protected DateTimeFormatter createDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(DATE_FORMAT);
    }
}
