package com.example.training.common.guice;

import com.example.training.common.handler.Printer;
import com.google.inject.AbstractModule;

public class PrinterModule extends AbstractModule {

    protected Printer printer;

    @Override
    protected void configure() {
        bindPrinter();
    }

    protected void bindPrinter() {
        printer = new Printer(System.out);

        bind(Printer.class).toInstance(printer);
    }
}
