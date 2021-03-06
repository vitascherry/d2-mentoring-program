package com.example.training.consolecommon.guice;

import com.example.training.consolecommon.handler.Printer;
import com.example.training.consolecommon.handler.impl.PrinterImpl;
import com.google.inject.AbstractModule;

public class PrinterModule extends AbstractModule {

    @Override
    protected void configure() {
        Printer printer = createPrinter();
        bind(Printer.class).toInstance(printer);
    }

    protected Printer createPrinter() {
        return new PrinterImpl(System.out);
    }
}
