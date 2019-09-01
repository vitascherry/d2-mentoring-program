package com.example.training.exchangeservicedemo.handler;

import com.example.training.consolecommon.handler.Handler;
import com.example.training.consolecommon.handler.Printer;
import com.example.training.consolecommon.handler.Reader;
import com.example.training.exchange.exception.MissingExchangeRateException;
import com.example.training.exchange.service.ExchangeRateService;
import lombok.Builder;
import lombok.SneakyThrows;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.example.training.exchange.constant.ExchangeConstants.SYSTEM_CURRENCY_CODE;
import static java.util.Currency.getInstance;

@Builder
public class ExchangeDemoHandler implements Handler {

    private final ExchangeRateService exchangeRateService;
    private final DateTimeFormatter dateTimeFormatter;
    private final DecimalFormat decimalFormat;
    private final Printer printer;
    private final Reader reader;

    private void printGreeting() {
        printer.println("Welcome to our Exchange Service");
        printer.println();
    }

    @SneakyThrows(MissingExchangeRateException.class)
    private void printCurrentExchangeRates() {
        printer.println("Testing NBU API...");

        printer.printf("Exchange rates for %s", dateTimeFormatter.format(LocalDate.now()));
        printer.println();
        decimalFormat.applyPattern("###,###.##### " + SYSTEM_CURRENCY_CODE);
        printer.printf("USD: %s", decimalFormat.format(exchangeRateService.getCurrentRate(getInstance("USD"))));
        printer.println();

        printer.println();
    }

    private void printGoodbye() {
        printer.println("Thanks for using our Exchange Service");
    }

    @Override
    public void handle(String[] args) {
        printGreeting();
        printCurrentExchangeRates();
        printGoodbye();
    }
}
