package com.example.training.totodemo;

import com.example.training.common.guice.Guicified;
import com.example.training.common.guice.annotation.WithModules;
import com.example.training.consolecommon.handler.Handler;
import com.example.training.totodemo.guice.TotoDemoModule;
import com.google.inject.Injector;

@WithModules(TotoDemoModule.class)
public class GuiceBootstrap implements Guicified {

    private final Handler handler;

    private GuiceBootstrap() {
        final Injector injector = getInjector();
        this.handler = injector.getInstance(Handler.class);
    }

    public static void main(String[] args) {
        GuiceBootstrap app = new GuiceBootstrap();
        app.handler.handle(args);
    }
}
