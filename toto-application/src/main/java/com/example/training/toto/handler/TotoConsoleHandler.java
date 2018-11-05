package com.example.training.toto.handler;

import com.example.training.common.handler.ConsoleHandler;
import com.example.training.common.service.DateTimeService;
import com.example.training.toto.domain.BetResult;
import com.example.training.toto.domain.Outcome;
import com.example.training.toto.domain.OutcomeSet;
import com.example.training.toto.domain.Price;
import com.example.training.toto.domain.Round;
import com.example.training.toto.domain.Wager;
import com.example.training.toto.exception.RoundNotFoundException;
import com.example.training.toto.service.TotoService;
import lombok.Builder;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

import static com.example.training.toto.constant.TotoConstants.DATE_FORMAT;
import static java.util.Arrays.stream;
import static java.util.regex.Pattern.CASE_INSENSITIVE;

@Builder
public class TotoConsoleHandler extends ConsoleHandler {

    private static final Pattern DATE_PATTERN = Pattern.compile("\\d\\d\\d\\d.\\d\\d.\\d\\d.");
    private static final Pattern OUTCOMES_PATTERN = Pattern.compile("[12X]{14}", CASE_INSENSITIVE);
    private static final Scanner SCANNER = new Scanner(System.in);

    private final DecimalFormat decimalFormatter;
    private final DateTimeService dateTimeService;
    private final TotoService totoService;

    public void printGreetings() {
        printWithLineBreak("Hi, user!");
        printWithLineBreak("Welcome to my sport betting game.");
        printWithLineBreak("Loading data from database...");
        printWithLineBreak("Testing...");
        printLineBreak();
    }

    public void printLargestPriceEver() {
        printWithLineBreak("Printing the largest price ever recorded...");

        Price price = totoService.getLargestPrice();
        decimalFormatter.applyPattern("###,### " + price.getCurrency());
        printWithLineBreak(decimalFormatter.format(price.getAmount()));

        printLineBreak();
    }

    public void printFullDistribution() {
        printWithLineBreak("Printing the correct distribution of the 1/2/X results of each round...");

        decimalFormatter.applyPattern("00.00 %");
        totoService.getDistributions().forEach(distribution ->
                printWithLineBreak("team #1 won: %s, team #2 won: %s, draw: %s",
                        decimalFormatter.format(distribution.getFirst()),
                        decimalFormatter.format(distribution.getSecond()),
                        decimalFormatter.format(distribution.getDraw()))
        );

        printLineBreak();
    }

    public void calculateAndPrintWager() {
        printWithLineBreak("Calculate and print the hits and amount for the specified wager...");

        Round round = getExistingRound();
        OutcomeSet outcomeSet = getOutcomeSet();
        BetResult betResult = totoService.calculateWager(new Wager(round, outcomeSet));

        decimalFormatter.applyPattern("###,### " + betResult.getPrice().getCurrency());
        printWithLineBreak("Result: hits: %d, amount: %s", betResult.getHits().size(),
                decimalFormatter.format(betResult.getPrice().getAmount()));

        printLineBreak();
    }

    public void printGoodbye() {
        printWithLineBreak("Thank you for using my app");
        printWithLineBreak("Good luck!");
    }

    private Round getExistingRound() {
        while (true) {
            try {
                String dateString = readWithRetry(DATE_PATTERN, String.format("Enter date (%s): ", DATE_FORMAT),
                        "Please enter date in proper format!");
                LocalDate date = dateTimeService.parse(dateString);
                return totoService.getRoundByDate(date);
            } catch (RoundNotFoundException e) {
                printWithLineBreak("Not found any round on %s", dateTimeService.format(e.getDate()));
            }
        }
    }

    private OutcomeSet getOutcomeSet() {
        String outcomesString = readWithRetry(OUTCOMES_PATTERN, "Enter outcomes (1/2/X): ",
                "Please enter outcomes in proper format!");
        return new OutcomeSet(
                stream(outcomesString.split(""))
                        .map(Outcome::fromValue)
                        .toArray(Outcome[]::new)
        );
    }
}
