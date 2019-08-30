package com.example.training.consolecommon.handler.impl;

import com.example.training.consolecommon.handler.Printer;
import lombok.AllArgsConstructor;

import java.io.PrintStream;

@AllArgsConstructor
public class PrinterImpl implements Printer {

    private final PrintStream out;

    @Override
    public void println(String message) {
        out.println(message);
    }

    @Override
    public void println() {
        out.println();
    }

    @Override
    public void printf(String message, Object... params) {
        out.print(String.format(message, params));
    }

    @Override
    public void print(String message) {
        out.print(message);
    }
}
