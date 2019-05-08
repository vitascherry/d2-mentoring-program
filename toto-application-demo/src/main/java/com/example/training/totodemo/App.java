package com.example.training.totodemo;

import com.example.training.common.guice.Guicified;
import com.example.training.common.guice.annotation.WithModules;
import com.example.training.consolecommon.handler.Handler;
import com.example.training.totodemo.guice.TotoDemoModule;
import com.google.inject.Injector;

@WithModules(TotoDemoModule.class)
public class App implements Guicified {

    private final Handler consoleHandler;

    private App() {
        final Injector injector = getInjector();
        this.consoleHandler = injector.getInstance(Handler.class);
    }

    public static void main(String[] args) {
        App app = new App();
        app.consoleHandler.handle(args);
    }
}
