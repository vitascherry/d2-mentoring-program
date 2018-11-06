package com.example.training.toto.handler;

import com.example.training.common.handler.Handler;
import com.example.training.common.handler.Printer;
import com.example.training.common.handler.REPLFunction;
import com.example.training.common.handler.Reader;
import com.example.training.common.service.DateTimeService;
import com.example.training.toto.domain.*;
import com.example.training.toto.service.TotoService;
import lombok.Builder;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Objects;

import static com.example.training.common.util.StringUtils.join;
import static com.example.training.toto.constant.TotoConstants.DATE_FORMAT;
import static java.util.Arrays.stream;

public class TotoConsoleHandler extends Handler {

    private final DecimalFormat decimalFormatter;
    private final DateTimeService dateTimeService;
    private final TotoService totoService;

    @Builder
    public TotoConsoleHandler(Printer printer, Reader reader, DecimalFormat decimalFormatter,
                              DateTimeService dateTimeService, TotoService totoService) {
        super(printer, reader);
        this.decimalFormatter = decimalFormatter;
        this.dateTimeService = dateTimeService;
        this.totoService = totoService;
    }

    private void printGreetings() {
        printer.println("Hi, user!");
        printer.println("Welcome to my sport betting game.");
        printer.println("Loading data from database...");
        printer.println("Testing...");
        printer.println();
    }

    private void printLargestPriceEver() {
        printer.println("Printing the largest price ever recorded...");

        Price price = totoService.getLargestPrice();
        decimalFormatter.applyPattern("###,###.## " + price.getCurrency());
        printer.println("The largest price is: %s", decimalFormatter.format(price.getAmount()));

        printer.println();
    }

    private void printFullDistribution() {
        printer.println("Printing the correct distribution of the results of each round...");

        decimalFormatter.applyPattern("00.00 %");
        totoService.getDistributions().forEach(distribution ->
                printer.println("team #1 won: %s, team #2 won: %s, draw: %s",
                        decimalFormatter.format(distribution.getFirst()),
                        decimalFormatter.format(distribution.getSecond()),
                        decimalFormatter.format(distribution.getDraw()))
        );

        printer.println();
    }

    private void calculateAndPrintWager() {
        printer.println("Calculate and print the hits and amount for the specified wager...");

        Round round = new REPLFunction<LocalDate, Round>(printer, reader)
                .withLoop()
                .withMessage("Enter date (%s): ", DATE_FORMAT)
                .withParser(dateTimeService::parse)
                .withErrorMessage("The date should be in proper format!")
                .withCondition(totoService::hasRound)
                .withBadMessage("Not found any round on '%s'")
                .withFilter(totoService::getRound)
                .eval();

        OutcomeSet outcomeSet = new REPLFunction<Outcome[], OutcomeSet>(printer, reader)
                .withLoop()
                .withMessage("Enter outcomes (%s): ", join("|", Outcome.values(), Outcome::getValue))
                .withParser(text -> stream(text.split(""))
                        .map(Outcome::fromValue)
                        .filter(Objects::nonNull)
                        .toArray(Outcome[]::new))
                .withErrorMessage("Could not parse '%s' as an array")
                .withCondition(outcomes -> outcomes.length == 14)
                .withBadMessage("Outcomes count should be 14!")
                .withFilter(OutcomeSet::new)
                .eval();

        BetResult betResult = totoService.calculateWager(new Wager(round, outcomeSet));

        decimalFormatter.applyPattern("###,### " + betResult.getPrice().getCurrency());
        printer.println("Result: hits: %d, amount: %s", betResult.getHits().size(),
                decimalFormatter.format(betResult.getPrice().getAmount()));

        printer.println();
    }

    private void printGoodbye() {
        printer.println("Thank you for using my app");
        printer.println("Good luck!");
    }

    @Override
    public void handle(String[] args) {
        printGreetings();
        printLargestPriceEver();
        printFullDistribution();
        calculateAndPrintWager();
        printGoodbye();
    }
}
