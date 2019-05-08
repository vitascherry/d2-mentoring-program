package com.example.training.sportsbettingdemo;

import com.example.training.common.guice.Guicified;
import com.example.training.common.guice.annotation.WithModules;
import com.example.training.sportsbettingdemo.guice.SportsBettingDemoModule;
import com.example.training.sportsbettingdemo.handler.SportsBettingConsoleHandler;

@WithModules(SportsBettingDemoModule.class)
public class App implements Guicified {

    private final SportsBettingConsoleHandler consoleHandler;

    private App() {
        consoleHandler = getInjector().getInstance(SportsBettingConsoleHandler.class);
    }

    public static void main(String[] args) {
        App app = new App();
        app.consoleHandler.handle(args);
    }
}
