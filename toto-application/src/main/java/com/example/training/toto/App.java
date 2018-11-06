package com.example.training.toto;

import com.example.training.common.guice.Guicified;
import com.example.training.common.guice.annotation.WithModules;
import com.example.training.common.handler.Handler;
import com.example.training.toto.guice.TotoModule;
import com.example.training.toto.handler.TotoConsoleHandler;
import com.google.inject.Injector;

@WithModules(TotoModule.class)
public class App implements Guicified {

    private Handler consoleHandler;

    private App() {
        final Injector injector = getInjector();
        this.consoleHandler = injector.getInstance(TotoConsoleHandler.class);
    }

    public static void main(String[] args) {
        App app = new App();
        app.consoleHandler.handle(args);
    }
}
