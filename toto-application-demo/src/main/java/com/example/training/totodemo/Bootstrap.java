package com.example.training.totodemo;

import com.example.training.common.guice.Guicified;
import com.example.training.common.guice.annotation.WithModules;
import com.example.training.consolecommon.handler.Handler;
import com.example.training.totodemo.guice.TotoDemoModule;
import com.google.inject.Injector;

@WithModules(TotoDemoModule.class)
public class Bootstrap implements Guicified {

    private final Handler handler;

    private Bootstrap() {
        final Injector injector = getInjector();
        this.handler = injector.getInstance(Handler.class);
    }

    public static void main(String[] args) {
        Bootstrap app = new Bootstrap();
        app.handler.handle(args);
    }
}
