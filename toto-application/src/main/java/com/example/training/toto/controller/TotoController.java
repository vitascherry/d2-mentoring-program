package com.example.training.toto.controller;

import com.example.training.common.service.DateTimeService;
import com.example.training.toto.domain.*;
import com.example.training.toto.exception.RoundNotFoundException;
import com.example.training.toto.service.TotoService;
import lombok.Builder;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

import static com.example.training.toto.constant.TotoConstants.DATE_FORMAT;
import static java.util.Arrays.stream;
import static java.util.regex.Pattern.CASE_INSENSITIVE;

@Builder
public class TotoController {

    private static final Pattern DATE_PATTERN = Pattern.compile("\\d\\d\\d\\d.\\d\\d.\\d\\d.");
    private static final Pattern OUTCOMES_PATTERN = Pattern.compile("[12X]{14}", CASE_INSENSITIVE);
    private static final Scanner SCANNER = new Scanner(System.in);

    private final DecimalFormat decimalFormatter;
    private final DateTimeService dateTimeService;
    private final TotoService totoService;

    public void printLargestPriceEver() {
        System.out.println("Printing the largest price ever recorded...");

        Price price = totoService.getLargestPrice();
        decimalFormatter.applyPattern("###,### " + price.getCurrency());
        System.out.println(decimalFormatter.format(price.getAmount()));

        System.out.println();
    }

    public void printFullDistribution() {
        System.out.println("Printing the correct distribution of the 1/2/X results of each round...");

        decimalFormatter.applyPattern("##.## %");
        totoService.getDistributions().forEach(distribution ->
                System.out.println("team #1 won: " + decimalFormatter.format(distribution.getFirst()) +
                        ", team #2 won: " + decimalFormatter.format(distribution.getSecond()) +
                        ", draw: " + decimalFormatter.format(distribution.getDraw()))
        );

        System.out.println();
    }

    public void calculateAndPrintWager() {
        System.out.println("Calculate and print the hits and amount for the specified wager...");

        Round round = getExistingRound();
        OutcomeSet outcomeSet = getOutcomeSet();
        BetResult betResult = totoService.calculateWager(new Wager(round, outcomeSet));

        decimalFormatter.applyPattern("###,### " + betResult.getPrice().getCurrency());
        System.out.println("Result: hits: " + betResult.getHits().size() + ", amount: " +
                decimalFormatter.format(betResult.getPrice().getAmount()));

        System.out.println();
    }

    private Round getExistingRound() {
        while (true) {
            try {
                String dateString = readWithRetry(DATE_PATTERN, String.format("Enter date (%s): ", DATE_FORMAT),
                        "Please enter date in proper format!");
                LocalDate date = dateTimeService.parse(dateString);
                return totoService.getRoundByDate(date);
            } catch (RoundNotFoundException e) {
                System.out.println(String.format("Not found any round on %s", dateTimeService.format(e.getDate())));
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

    private String readWithRetry(Pattern pattern, String message, String errorMessage) {
        while (true) {
            try {
                System.out.print(message);
                return SCANNER.next(pattern);
            } catch (NoSuchElementException e) {
                System.out.println(errorMessage);
                SCANNER.nextLine();
            }
        }
    }
}
