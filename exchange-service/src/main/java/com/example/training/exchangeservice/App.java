package com.example.training.exchangeservice;

import com.example.training.common.guice.Guicified;
import com.example.training.common.guice.annotation.WithModules;
import com.example.training.exchangeservice.guice.ExchangeModule;
import com.example.training.exchangeservice.handler.ExchangeHandler;

@WithModules(ExchangeModule.class)
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
