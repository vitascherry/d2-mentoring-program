package com.example.training.consolecommon.handler;

public interface Printer {

    void println(String message);

    void println();

    void printf(String message, Object... params);

    void print(String message);
}
