package com.example.training.toto;

import com.example.training.common.guice.Guicified;
import com.example.training.common.guice.annotation.WithModules;
import com.example.training.toto.controller.TotoController;
import com.example.training.toto.guice.TotoServiceModule;
import com.google.inject.Injector;

@WithModules(TotoServiceModule.class)
public class App implements Guicified {

    private TotoController totoController;

    private App() {
        final Injector injector = getInjector();
        this.totoController = injector.getInstance(TotoController.class);
    }

    public static void main(String[] args) {
        System.out.println("Hi, user!");
        System.out.println("Welcome to my sport betting game.");
        System.out.println("Loading data from database...");
        System.out.println("Testing...");
        System.out.println();

        App app = new App();
        app.totoController.printLargestPriceEver();
        app.totoController.printFullDistribution();
        app.totoController.calculateAndPrintWager();

        System.out.println("Thank you for using my app");
        System.out.println("Good luck!");
    }
}
