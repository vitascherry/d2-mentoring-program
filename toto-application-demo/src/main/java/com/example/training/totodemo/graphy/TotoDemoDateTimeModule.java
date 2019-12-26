package com.example.training.totodemo.graphy;

import com.example.training.graphy.annotation.Provides;
import com.example.training.graphy.key.Key;
import com.example.training.graphy.key.Scope;
import com.example.training.graphy.module.AnnotationDrivenModule;

import javax.inject.Named;
import javax.inject.Singleton;
import java.time.format.DateTimeFormatter;

import static com.example.training.toto.constant.TotoConstants.DATE_FORMAT;

public class TotoDemoDateTimeModule extends AnnotationDrivenModule {

    static final Key DATE_TIME_FORMATTER_KEY = Key.builder()
            .type(DateTimeFormatter.class)
            .name("totoDemoDateTimeFormatter")
            .scope(Scope.SINGLETON)
            .build();

    @Provides
    @Singleton
    @Named("totoDemoDateTimeFormatter")
    public DateTimeFormatter createDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(DATE_FORMAT);
    }
}
