package com.example.training.sportsbettingdemo;

import com.example.training.common.guice.Guicified;
import com.example.training.common.guice.annotation.WithModules;
import com.example.training.consolecommon.handler.Handler;
import com.example.training.sportsbettingdemo.guice.SportsBettingDemoModule;

@WithModules(SportsBettingDemoModule.class)
public class Bootstrap implements Guicified {

    private final Handler consoleHandler;

    private Bootstrap() {
        consoleHandler = getInjector().getInstance(Handler.class);
    }

    public static void main(String[] args) {
        Bootstrap app = new Bootstrap();
        app.consoleHandler.handle(args);
    }
}
