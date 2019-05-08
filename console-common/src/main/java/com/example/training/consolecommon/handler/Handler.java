package com.example.training.consolecommon.handler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Handler {

    protected final Printer printer;
    protected final Reader reader;

    public abstract void handle(String[] args);
}
