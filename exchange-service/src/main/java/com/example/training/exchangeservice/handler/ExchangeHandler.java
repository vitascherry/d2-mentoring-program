package com.example.training.exchangeservice.handler;

import com.example.training.common.handler.Handler;
import com.example.training.common.handler.Printer;
import com.example.training.common.handler.Reader;
import com.example.training.common.service.DateTimeService;
import com.example.training.exchangeservice.service.ExchangeRateService;
import lombok.Builder;

import java.text.DecimalFormat;
import java.time.LocalDate;

import static com.example.training.exchangeservice.constant.ExchangeConstants.SYSTEM_CURRENCY_CODE;
import static java.util.Currency.getInstance;

public class ExchangeHandler extends Handler {

    private final ExchangeRateService exchangeRateService;
    private final DateTimeService dateTimeService;
    private final DecimalFormat decimalFormatter;

    @Builder
    public ExchangeHandler(Printer printer, Reader reader, ExchangeRateService exchangeRateService,
                           DateTimeService dateTimeService, DecimalFormat decimalFormatter) {
        super(printer, reader);
        this.exchangeRateService = exchangeRateService;
        this.dateTimeService = dateTimeService;
        this.decimalFormatter = decimalFormatter;
    }

    private void printGreeting() {
        printer.println("Welcome to our Exchange Service");
        printer.println();
    }

    private void printCurrentExchangeRates() {
        printer.println("Testing NBU API...");

        printer.println("Exchange rates for %s", dateTimeService.format(LocalDate.now()));
        decimalFormatter.applyPattern("###,###.##### " + SYSTEM_CURRENCY_CODE);
        printer.println("USD: %s", decimalFormatter.format(exchangeRateService.getCurrentRate(getInstance("USD"))));
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
