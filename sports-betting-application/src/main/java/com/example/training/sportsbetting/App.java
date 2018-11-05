package com.example.training.sportsbetting;

import com.example.training.common.guice.Guicified;
import com.example.training.common.guice.annotation.WithModules;
import com.example.training.sportsbetting.guice.SportsBettingModule;
import com.example.training.sportsbetting.handler.SportsBettingConsoleHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@WithModules(SportsBettingModule.class)
public class App implements Guicified {

    private final SportsBettingConsoleHandler consoleHandler;

    private App() {
        consoleHandler = getInjector().getInstance(SportsBettingConsoleHandler.class);
    }

    public static void main(String[] args) {
        App app = new App();
        app.consoleHandler.printGreetings();
        app.consoleHandler.printAllBetsWithPossibleOutcomes();
        app.consoleHandler.printGoodbye();
    }
}
