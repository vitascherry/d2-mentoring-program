package com.example.training.exchangeservicedemo;

import com.example.training.common.guice.Guicified;
import com.example.training.common.guice.annotation.WithModules;
import com.example.training.exchangeservicedemo.guice.ExchangeDemoModule;
import com.example.training.exchangeservicedemo.handler.ExchangeHandler;

@WithModules(ExchangeDemoModule.class)
public class App implements Guicified {

    private final ExchangeHandler exchangeConsoleHandler;

    private App() {
        this.exchangeConsoleHandler = getInjector().getInstance(ExchangeHandler.class);
    }

    public static void main(String[] args) {
        App app = new App();

        app.exchangeConsoleHandler.handle(args);
    }
}
