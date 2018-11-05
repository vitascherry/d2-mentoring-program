package com.example.training.sportsbetting.handler;

import com.example.training.common.handler.ConsoleHandler;
import com.example.training.sportsbetting.domain.Bet;
import com.example.training.sportsbetting.domain.Outcome;
import com.example.training.sportsbetting.domain.OutcomeOdd;
import com.example.training.sportsbetting.domain.SportEvent;
import com.example.training.sportsbetting.service.SportEventService;
import lombok.AllArgsConstructor;

import java.text.DecimalFormat;
import java.util.Optional;

import static java.util.Comparator.comparing;

@AllArgsConstructor
public class SportsBettingConsoleHandler extends ConsoleHandler {

    private final SportEventService sportEventService;
    private final DecimalFormat decimalFormatter;

    public void printGreetings() {
        printWithLineBreak("Welcome, dear Player!");
        printWithLineBreak("It's Sports Betting Game");
        printWithLineBreak("Look, what sport events did we prepare for you!");

        printLineBreak();
    }

    public void printAllBetsWithPossibleOutcomes() {
        decimalFormatter.applyPattern("###,###.##");
        decimalFormatter.setGroupingUsed(false);

        printWithLineBreak("Listing all bets with possible outcomes...");
        for (SportEvent sportEvent : sportEventService.getSportEvents()) {
            printWithLineBreak("#%s. %s", sportEvent.getIdentifier().getId(), sportEvent.getTitle());
            for (Bet bet : sportEvent.getBets()) {
                printWithLineBreak("    #%s. %s", bet.getIdentifier().getId(),
                        Optional.ofNullable(bet.getDescription()).orElse("-"));
                for (Outcome outcome : bet.getOutcomes()) {
                    printWithLineBreak("        #%s. outcome: %s, odd: %s", outcome.getIdentifier().getId(),
                            outcome.getValue(),
                            outcome.getOdds()
                                    .stream()
                                    .max(comparing(OutcomeOdd::getFrom))
                                    .map(OutcomeOdd::getOdd)
                                    .map(decimalFormatter::format)
                                    .orElse("-"));
                }
            }
        }

        printLineBreak();
    }

    public void printGoodbye() {
        printWithLineBreak("Thank you for using our app!");
    }
}
