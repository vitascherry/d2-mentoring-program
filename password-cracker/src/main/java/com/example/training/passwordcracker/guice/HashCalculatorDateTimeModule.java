package com.example.training.passwordcracker.guice;

import com.example.training.common.guice.DateTimeModule;

import java.time.format.DateTimeFormatter;

import static com.example.training.passwordcracker.constant.HashCalculatorConstants.DATE_TIME_FORMAT;

public class HashCalculatorDateTimeModule extends DateTimeModule {

    @Override
    protected void bindDateTimeFormatter() {
        dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

        bind(DateTimeFormatter.class).toInstance(dateTimeFormatter);
    }
}
