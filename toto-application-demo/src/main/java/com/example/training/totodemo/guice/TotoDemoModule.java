package com.example.training.totodemo.guice;

import com.example.training.common.mapper.EntityMapper;
import com.example.training.consolecommon.guice.ConsoleCommonModule;
import com.example.training.consolecommon.handler.Handler;
import com.example.training.consolecommon.handler.Printer;
import com.example.training.consolecommon.handler.Reader;
import com.example.training.toto.domain.Outcome;
import com.example.training.toto.domain.OutcomeSet;
import com.example.training.toto.guice.TotoAggregateModule;
import com.example.training.toto.service.TotoService;
import com.example.training.totodemo.annotation.ValidateOutcomes;
import com.example.training.totodemo.guice.aop.ValidateOutcomesMethodInterceptor;
import com.example.training.totodemo.handler.TotoDemoHandler;
import com.example.training.totodemo.mapper.OutcomeSetMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Named;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public class TotoDemoModule extends AbstractModule {

    private static final TypeLiteral<EntityMapper<Outcome[], OutcomeSet>> ENTITY_MAPPER_TYPE_LITERAL = new TypeLiteral<EntityMapper<Outcome[], OutcomeSet>>() {};

    @Override
    protected void configure() {
        bind(ENTITY_MAPPER_TYPE_LITERAL).to(OutcomeSetMapper.class);
        // Using AOP to verify outcomes array size
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(ValidateOutcomes.class), new ValidateOutcomesMethodInterceptor());

        install(new ConsoleCommonModule());
        install(new TotoDemoDateTimeModule());
        install(new TotoDemoDecimalModule());
        install(new TotoAggregateModule());
    }

    @Singleton
    @Provides
    public Handler totoConsoleHandlerProvider(Printer printer, Reader reader, TotoService totoService,
                                              EntityMapper<Outcome[], OutcomeSet> outcomeSetMapper,
                                              @Named("totoDemoDecimalFormat") DecimalFormat decimalFormat,
                                              @Named("totoDemoDateTimeFormatter") DateTimeFormatter dateTimeFormatter) {
        return TotoDemoHandler.builder()
                .printer(printer)
                .reader(reader)
                .decimalFormat(decimalFormat)
                .dateTimeFormatter(dateTimeFormatter)
                .totoService(totoService)
                .outcomeSetMapper(outcomeSetMapper)
                .build();
    }
}
