package com.example.training.exchangeservice;

import com.example.training.common.guice.Guicified;
import com.example.training.common.guice.annotation.WithModules;
import com.example.training.exchangeservice.guice.ExchangeModule;
import com.example.training.exchangeservice.handler.ExchangeConsoleHandler;

@WithModules(ExchangeModule.class)
public class App implements Guicified {

    private final ExchangeConsoleHandler exchangeConsoleHandler;

    private App() {
        this.exchangeConsoleHandler = getInjector().getInstance(ExchangeConsoleHandler.class);
    }

    public static void main(String[] args) {
        App app = new App();

        app.exchangeConsoleHandler.printGreeting();
        app.exchangeConsoleHandler.printCurrentExchangeRates();
        app.exchangeConsoleHandler.printGoodbye();
    }
}
