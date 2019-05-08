package com.example.training.consolecommon.guice;

import com.google.inject.AbstractModule;

public class ConsoleCommonModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new PrinterModule());
        install(new ReaderModule());
    }
}
