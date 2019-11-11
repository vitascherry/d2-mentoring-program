package com.example.training.totodemo.graphy;

import com.example.training.common.graphy.DateTimeModule;
import com.example.training.graphy.factory.SingletonFactory;
import com.example.training.graphy.key.Key;
import com.example.training.graphy.linker.Linker;

import java.time.format.DateTimeFormatter;

import static com.example.training.toto.constant.TotoConstants.DATE_FORMAT;

public class TotoDemoDateTimeModule extends DateTimeModule {

    static final Key<DateTimeFormatter> DATE_TIME_FORMATTER_KEY = Key.<DateTimeFormatter>builder()
            .withClass(DateTimeFormatter.class)
            .withName("totoDemoDateTimeFormatter")
            .build();

    @Override
    public void configure(Linker linker) {
        linker.install(DATE_TIME_FORMATTER_KEY, SingletonFactory.of(this::createDateTimeFormatter));
    }

    @Override
    protected DateTimeFormatter createDateTimeFormatter(Linker linker) {
        return DateTimeFormatter.ofPattern(DATE_FORMAT);
    }
}
