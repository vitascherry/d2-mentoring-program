package com.example.training.sportsbettingdemo.guice;

import com.example.training.consolecommon.guice.ConsoleCommonModule;
import com.example.training.consolecommon.handler.Handler;
import com.example.training.consolecommon.handler.Printer;
import com.example.training.consolecommon.handler.Reader;
import com.example.training.sportsbetting.guice.SportsBettingAggregateModule;
import com.example.training.sportsbetting.service.SportsBettingService;
import com.example.training.sportsbettingdemo.handler.SportsBettingDemoHandler;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import java.text.DecimalFormat;

public class SportsBettingDemoModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ConsoleCommonModule());
        install(new SportsBettingDemoDecimalModule());
        install(new SportsBettingAggregateModule());
    }

    @Singleton
    @Provides
    public Handler sportsBettingConsoleHandlerProvider(Printer printer, Reader reader,
                                                       SportsBettingService sportsBettingService,
                                                       @Named("sportsBettingDemoDecimalFormat") DecimalFormat decimalFormat) {
        return SportsBettingDemoHandler.builder()
                .printer(printer)
                .reader(reader)
                .sportsBettingService(sportsBettingService)
                .decimalFormat(decimalFormat)
                .build();
    }
}
