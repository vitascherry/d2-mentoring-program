package com.example.training.toto;

import com.example.training.common.guice.Guicified;
import com.example.training.common.guice.annotation.WithModules;
import com.example.training.toto.handler.TotoConsoleHandler;
import com.example.training.toto.guice.TotoServiceModule;
import com.google.inject.Injector;

@WithModules(TotoServiceModule.class)
public class App implements Guicified {

    private TotoConsoleHandler totoConsoleHandler;

    private App() {
        final Injector injector = getInjector();
        this.totoConsoleHandler = injector.getInstance(TotoConsoleHandler.class);
    }

    public static void main(String[] args) {
        App app = new App();
        app.totoConsoleHandler.printGreetings();
        app.totoConsoleHandler.printLargestPriceEver();
        app.totoConsoleHandler.printFullDistribution();
        app.totoConsoleHandler.calculateAndPrintWager();
        app.totoConsoleHandler.printGoodbye();
    }
}
