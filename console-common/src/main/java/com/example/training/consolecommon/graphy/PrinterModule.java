package com.example.training.consolecommon.graphy;

import com.example.training.consolecommon.handler.Printer;
import com.example.training.consolecommon.handler.impl.PrinterImpl;
import com.example.training.graphy.factory.SingletonFactory;
import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;

public class PrinterModule implements Module {

    @Override
    public void configure(Linker linker) {
        linker.install(Printer.class, SingletonFactory.of(this::createPrinter));
    }

    protected Printer createPrinter(Linker linker) {
        return new PrinterImpl(System.out);
    }
}
