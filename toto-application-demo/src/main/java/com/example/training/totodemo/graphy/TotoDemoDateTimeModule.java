package com.example.training.totodemo.graphy;

import com.example.training.graphy.key.Key;
import com.example.training.graphy.linker.Linker;
import com.example.training.common.graphy.module.DateTimeModule;

import java.time.format.DateTimeFormatter;

public class TotoDemoDateTimeModule extends DateTimeModule {

    static final Key<DateTimeFormatter> DATE_TIME_FORMATTER_KEY = Key.<DateTimeFormatter>builder()
            .withClass(DateTimeFormatter.class)
            .withName("totoDemoDateTimeFormatter")
            .build();

    @Override
    public void configure(Linker linker) {
        linker.install(DATE_TIME_FORMATTER_KEY, this::createDateTimeFormatter);
    }
}
