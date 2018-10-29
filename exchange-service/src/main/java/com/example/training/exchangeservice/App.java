package com.example.training.exchangeservice;

import com.example.training.common.guice.Guicified;
import com.example.training.common.guice.annotation.WithModules;
import com.example.training.exchangeservice.guice.ExchangeModule;
import com.example.training.exchangeservice.service.ExchangeRateService;

import java.util.Currency;

import static com.example.training.exchangeservice.constant.ExchangeConstants.SYSTEM_CURRENCY_CODE;


@WithModules(ExchangeModule.class)
public class App implements Guicified {

    private ExchangeRateService exchangeRateService;

    private App() {
        this.exchangeRateService = getInjector().getInstance(ExchangeRateService.class);
    }

    public static void main(String[] args) {
        App app = new App();

        System.out.println("Welcome to our Exchange Service");
        System.out.println("Testing NBU API...");
        System.out.println("Current rate for USD: " +
                app.exchangeRateService.getCurrentRate(Currency.getInstance("USD")) + " " + SYSTEM_CURRENCY_CODE);
        System.out.println("Thanks for using our Exchange Service");
    }
}
