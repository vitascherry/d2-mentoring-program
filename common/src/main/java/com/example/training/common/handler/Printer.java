package com.example.training.common.handler;

import lombok.AllArgsConstructor;

import java.io.PrintStream;

@AllArgsConstructor
public class Printer {

    private final PrintStream out;

    public void println(String message) {
        out.println(message);
    }

    public void println(String message, Object... params) {
        out.println(String.format(message, params));
    }

    public void println() {
        out.println();
    }

    public void print(String message) {
        out.print(message);
    }

    public void print(String message, Object... params) {
        out.print(String.format(message, params));
    }
}
