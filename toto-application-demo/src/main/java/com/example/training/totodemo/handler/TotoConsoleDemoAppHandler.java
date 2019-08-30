package com.example.training.totodemo.handler;

import com.example.training.consolecommon.handler.Handler;
import com.example.training.consolecommon.handler.Printer;
import com.example.training.consolecommon.handler.REPLFunction;
import com.example.training.consolecommon.handler.Reader;
import com.example.training.toto.domain.BetResult;
import com.example.training.toto.domain.Distribution;
import com.example.training.toto.domain.Outcome;
import com.example.training.toto.domain.OutcomeSet;
import com.example.training.toto.domain.Price;
import com.example.training.toto.domain.Round;
import com.example.training.toto.domain.Wager;
import com.example.training.toto.service.TotoService;
import com.example.training.totodemo.guice.mapper.OutcomeSetMapper;
import lombok.Builder;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

import static com.example.training.common.util.FunctionUtils.unchecked;
import static com.example.training.common.util.StringUtils.join;
import static com.example.training.toto.constant.TotoConstants.DATE_FORMAT;

@Builder
public class TotoConsoleDemoAppHandler implements Handler {

    private final DecimalFormat decimalFormat;
    private final DateTimeFormatter dateTimeFormatter;
    private final TotoService totoService;
    private final OutcomeSetMapper outcomeSetMapper;
    private final Printer printer;
    private final Reader reader;

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
        decimalFormat.applyPattern("###,###.## " + price.getCurrency());
        printer.printf("The largest price is: %s", decimalFormat.format(price.getAmount()));
        printer.println();

        printer.println();
    }

    private void printFullDistribution() {
        printer.println("Printing the correct distribution of the results of each round...");

        decimalFormat.applyPattern("00.00 %");
        for (Distribution distribution : totoService.getDistributions()) {
            printer.printf("team #1 won: %s, team #2 won: %s, draw: %s",
                    decimalFormat.format(distribution.getFirst()),
                    decimalFormat.format(distribution.getSecond()),
                    decimalFormat.format(distribution.getDraw()));
            printer.println();
        }

        printer.println();
    }

    private void calculateAndPrintWager() {
        printer.println("Calculate and print the hits and amount for the specified wager...");

        Round round = new REPLFunction<LocalDate, Round>(printer, reader)
                .withLoop()
                .withMessage("Enter date (%s): ", DATE_FORMAT)
                .withParser(date -> dateTimeFormatter.parse(date, LocalDate::from))
                .withErrorMessage("The date should be in proper format!")
                .withCondition(totoService::hasRound)
                .withBadMessage("Not found any round on %s")
                .withMapper(unchecked(totoService::getRound))
                .eval();

        OutcomeSet outcomeSet = new REPLFunction<Outcome[], OutcomeSet>(printer, reader)
                .withLoop()
                .withMessage("Enter outcomes (%s): ", join("|", Outcome.values()))
                .withParser(text -> Arrays.stream(text.split(""))
                        .map(Outcome::fromValue)
                        .filter(Objects::nonNull)
                        .toArray(Outcome[]::new))
                .withErrorMessage("Could not parse %s as an array")
                .withCondition(outcomes -> outcomes.length == 14)
                .withBadMessage("Outcomes count should be 14!")
                .withMapper(outcomeSetMapper::map)
                .eval();

        BetResult betResult = totoService.calculateWager(new Wager(round, outcomeSet));

        decimalFormat.applyPattern("###,### " + betResult.getPrice().getCurrency());
        printer.printf("Result: hits: %d, amount: %s", betResult.getHits().size(),
                decimalFormat.format(betResult.getPrice().getAmount()));
        printer.println();

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
