package com.example.training.passwordcracker.guice;

import com.example.training.common.guice.PrinterModule;
import com.example.training.common.guice.ReaderModule;
import com.example.training.common.handler.Handler;
import com.example.training.common.handler.Printer;
import com.example.training.common.handler.Reader;
import com.example.training.common.service.DateTimeService;
import com.example.training.passwordcracker.handler.HashCalculatorConsoleHandler;
import com.example.training.passwordcracker.service.HashCalculator;
import com.example.training.passwordcracker.service.PasswordCracker;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class HashCalculatorModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new HashCalculatorDateTimeModule());
        install(new PrinterModule());
        install(new ReaderModule());

        bind(Handler.class).to(HashCalculatorConsoleHandler.class);
    }

    @Singleton
    @Provides
    public HashCalculatorConsoleHandler hashCalculatorConsoleHandlerProvider(Printer printer, Reader reader,
                                                                             DateTimeService dateTimeService,
                                                                             PasswordCracker passwordCracker) {
        return HashCalculatorConsoleHandler.builder()
                .printer(printer)
                .reader(reader)
                .dateTimeService(dateTimeService)
                .passwordCracker(passwordCracker)
                .build();
    }

    @Singleton
    @Provides
    public PasswordCracker passwordCracker(HashCalculator hashCalculator) {
        return new PasswordCracker(hashCalculator);
    }
}
