package com.example.training.passwordcracker.guice;

import com.example.training.common.guice.DateTimeModule;

import java.time.format.DateTimeFormatter;

import static com.example.training.passwordcracker.constant.HashCalculatorConstants.DATE_TIME_FORMAT;

public class HashCalculatorDateTimeModule extends DateTimeModule {

    @Override
    protected DateTimeFormatter createDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    }
}
