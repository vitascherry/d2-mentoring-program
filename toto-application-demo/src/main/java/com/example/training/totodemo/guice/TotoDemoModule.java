package com.example.training.totodemo.guice;

import com.example.training.consolecommon.guice.ConsoleCommonModule;
import com.example.training.consolecommon.handler.Handler;
import com.example.training.consolecommon.handler.Printer;
import com.example.training.consolecommon.handler.Reader;
import com.example.training.toto.domain.OutcomeSet;
import com.example.training.toto.guice.TotoAggregateModule;
import com.example.training.toto.service.TotoService;
import com.example.training.totodemo.aop.ValidateOutcomesInterceptor;
import com.example.training.totodemo.aop.annotation.ValidateOutcomes;
import com.example.training.totodemo.guice.provider.OutcomeSetMapper;
import com.example.training.totodemo.handler.TotoConsoleHandler;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Named;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public class TotoDemoModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Handler.class).to(TotoConsoleHandler.class);

        bindInterceptor(Matchers.any(), Matchers.annotatedWith(ValidateOutcomes.class), new ValidateOutcomesInterceptor());
        bind(OutcomeSet.class).toProvider(OutcomeSetMapper.class);

        install(new ConsoleCommonModule());
        install(new TotoDemoDateTimeModule());
        install(new TotoAggregateModule());
    }

    @Singleton
    @Provides
    public TotoConsoleHandler totoConsoleHandlerProvider(Printer printer, Reader reader, TotoService totoService,
                                                         OutcomeSetMapper outcomeSetMapper,
                                                         @Named("totoDecimalFormat") DecimalFormat decimalFormat,
                                                         @Named("totoDemoDateTimeFormatter") DateTimeFormatter dateTimeFormatter) {
        return TotoConsoleHandler.builder()
                .printer(printer)
                .reader(reader)
                .decimalFormat(decimalFormat)
                .dateTimeFormatter(dateTimeFormatter)
                .totoService(totoService)
                .outcomeSetMapper(outcomeSetMapper)
                .build();
    }
}
