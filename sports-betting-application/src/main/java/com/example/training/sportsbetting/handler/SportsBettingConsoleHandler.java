package com.example.training.sportsbetting.handler;

import com.example.training.common.handler.ConsoleHandler;
import com.example.training.common.handler.REPLFunction;
import com.example.training.sportsbetting.domain.Bet;
import com.example.training.sportsbetting.domain.BetType;
import com.example.training.sportsbetting.domain.Outcome;
import com.example.training.sportsbetting.domain.SportEvent;
import com.example.training.sportsbetting.service.SportsBettingService;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import static com.example.training.common.util.CurrencyUtils.parseCurrencyCode;
import static com.example.training.common.util.ExceptionUtils.unchecked;
import static com.example.training.sportsbetting.util.OddUtils.getLatestOdd;
import static java.math.BigDecimal.ZERO;
import static java.util.Currency.getInstance;

@AllArgsConstructor
public class SportsBettingConsoleHandler extends ConsoleHandler {

    private final SportsBettingService sportsBettingService;
    private final DecimalFormat decimalFormatter;

    public void printGreetings() {
        printWithLineBreak("Welcome, dear Player!");
        printWithLineBreak("It's Sports Betting Game");
        printWithLineBreak("Look, what sport events did we prepare for you!");

        printLineBreak();
    }

    public void printAllBetsWithPossibleOutcomes() {
        printWithLineBreak("Listing all bets with possible outcomes...");
        decimalFormatter.applyPattern("###,###.##");
        decimalFormatter.setGroupingUsed(false);

        sportsBettingService.getSportEvents().forEach(sportEvent -> {
            printWithLineBreak("#%s. %s", sportEvent.getIdentifier().getId(), sportEvent.getTitle());

            sportEvent.getBets().forEach(bet -> {
                printWithLineBreak("    #%s. %s", bet.getIdentifier().getId(),
                        bet.getDescription() != null ? bet.getDescription() : bet.getType().name());

                bet.getOutcomes().forEach(outcome ->
                        printWithLineBreak("        #%s. outcome: %s, odd: %s", outcome.getIdentifier().getId(),
                                outcome.getValue(), getLatestOdd(outcome.getOdds())
                                        .map(decimalFormatter::format)
                                        .orElse("-")));
            });
        });

        printLineBreak();
    }

    public void calculateWagerForUserOutcomes() {
        printWithLineBreak("Let's start the Sports Betting game!");
        printWithLineBreak("You can bet on sport events by choosing one possible outcome of a bet " +
                "and specifying the amount you wish to wager");

        final List<SportEvent> possibleSportEvents = sportsBettingService.getSportEvents();
        SportEvent sportEvent = new REPLFunction<String, SportEvent>()
                .withLoop()
                .withMessage("Please, choose the sport's event name: ")
                .withFilter(title -> possibleSportEvents.stream()
                        .filter(event -> event.getTitle().equals(title))
                        .findFirst()
                        .orElse(null))
                .withCondition(title -> possibleSportEvents.stream()
                        .map(SportEvent::getTitle)
                        .anyMatch(title1 -> title1.equals(title)))
                .withErrorMessage("Not found any sport events with title '%s'")
                .eval();

        final List<Bet> possibleBets = sportEvent.getBets();
        Bet bet = new REPLFunction<BetType, Bet>()
                .withLoop()
                .withMessage("Please, choose bet type: ")
                .withParser(BetType::valueOf)
                .withFilter(type -> possibleBets.stream()
                        .filter(bet1 -> bet1.getType().equals(type))
                        .findFirst()
                        .orElse(null))
                .withCondition(type -> possibleBets.stream()
                        .map(Bet::getType)
                        .anyMatch(type1 -> type1.equals(type)))
                .withErrorMessage("Not found any bets with bet type '%s'")
                .eval();

        final List<Outcome> possibleOutcomes = bet.getOutcomes();
        Outcome outcome = new REPLFunction<String, Outcome>()
                .withLoop()
                .withMessage("Please, choose outcome: ")
                .withFilter(value -> possibleOutcomes.stream()
                        .filter(outcome1 -> outcome1.getValue().equals(value))
                        .findFirst()
                        .orElse(null))
                .withCondition(value -> possibleOutcomes.stream()
                        .map(Outcome::getValue)
                        .anyMatch(value1 -> value1.equals(value)))
                .withErrorMessage("Not found any outcomes with value '%s'")
                .eval();

        BigDecimal odd = getLatestOdd(outcome.getOdds())
                .map(BigDecimal::valueOf)
                .orElse(ZERO);
        decimalFormatter.applyPattern("###,###.##");
        printWithLineBreak("The odd is %s", decimalFormatter.format(odd));

        BigDecimal betPrice = new REPLFunction<BigDecimal, BigDecimal>()
                .withLoop()
                .withMessage("How much do you want to bet? ")
                .withParser(unchecked((String text) -> {
                    String currencyCode = parseCurrencyCode(text);
                    decimalFormatter.setCurrency(getInstance(currencyCode));
                    decimalFormatter.applyPattern("###,###.## " + currencyCode);
                    return text;
                }).andThen(unchecked((String text) -> (BigDecimal) decimalFormatter.parse(text))))
                .withErrorMessage("Please, specify an amount with a 3-letter currency code!")
                .eval();

        List<Outcome> sportEventResults = sportsBettingService.getSportEventResults(sportEvent);
        if (sportEventResults.contains(outcome)) {
            printWithLineBreak("Congrats! You won %s", decimalFormatter.format(betPrice.multiply(odd)));
        } else {
            printWithLineBreak("Oh! You've missed! Better luck next time, mate.");
        }

        printLineBreak();
    }

    public void printGoodbye() {
        printWithLineBreak("Thank you for using our app!");
    }
}
