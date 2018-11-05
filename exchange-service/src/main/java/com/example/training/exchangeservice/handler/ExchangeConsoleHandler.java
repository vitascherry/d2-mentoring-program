package com.example.training.exchangeservice.handler;

import com.example.training.common.handler.ConsoleHandler;
import com.example.training.common.service.DateTimeService;
import com.example.training.exchangeservice.service.ExchangeRateService;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

import static com.example.training.exchangeservice.constant.ExchangeConstants.SYSTEM_CURRENCY_CODE;
import static java.util.Currency.getInstance;

@AllArgsConstructor
public class ExchangeConsoleHandler extends ConsoleHandler {

    private final ExchangeRateService exchangeRateService;
    private final DateTimeService dateTimeService;

    public void printGreeting() {
        printWithLineBreak("Welcome to our Exchange Service");
        printLineBreak();
    }

    public void printCurrentExchangeRates() {
        printWithLineBreak("Testing NBU API...");
        printWithLineBreak("Exchange rates for %s", dateTimeService.format(LocalDate.now()));
        printWithLineBreak("USD: %s %s", exchangeRateService.getCurrentRate(getInstance("USD")), SYSTEM_CURRENCY_CODE);
        printLineBreak();
    }

    public void printGoodbye() {
        printWithLineBreak("Thanks for using our Exchange Service");
    }
}
