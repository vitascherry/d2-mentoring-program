package com.example.training.exchangeservicedemo;

import com.example.training.common.guice.Guicified;
import com.example.training.common.guice.annotation.WithModules;
import com.example.training.consolecommon.handler.Handler;
import com.example.training.exchangeservicedemo.guice.ExchangeDemoModule;

@WithModules(ExchangeDemoModule.class)
public class Bootstrap implements Guicified {

    private final Handler exchangeConsoleHandler;

    private Bootstrap() {
        this.exchangeConsoleHandler = getInjector().getInstance(Handler.class);
    }

    public static void main(String[] args) {
        Bootstrap app = new Bootstrap();

        app.exchangeConsoleHandler.handle(args);
    }
}
