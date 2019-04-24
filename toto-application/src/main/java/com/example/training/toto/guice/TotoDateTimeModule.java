package com.example.training.toto.guice;

import com.example.training.common.guice.DateTimeModule;

import java.time.format.DateTimeFormatter;

import static com.example.training.toto.constant.TotoConstants.DATE_FORMAT;

public class TotoDateTimeModule extends DateTimeModule {

    @Override
    protected DateTimeFormatter createDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(DATE_FORMAT);
    }
}
