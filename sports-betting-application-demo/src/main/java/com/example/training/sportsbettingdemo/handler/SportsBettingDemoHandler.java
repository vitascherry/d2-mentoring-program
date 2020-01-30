package com.example.training.sportsbettingdemo.handler;

import com.example.training.consolecommon.handler.Handler;
import com.example.training.consolecommon.handler.Printer;
import com.example.training.consolecommon.handler.REPLFunction;
import com.example.training.consolecommon.handler.Reader;
import com.example.training.sportsbetting.domain.Bet;
import com.example.training.sportsbetting.domain.BetType;
import com.example.training.sportsbetting.domain.Outcome;
import com.example.training.sportsbetting.domain.SportEvent;
import com.example.training.sportsbetting.service.SportsBettingService;
import lombok.Builder;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import static com.example.training.util.CurrencyUtils.parseCurrencyCode;
import static com.example.training.util.FunctionUtils.sneaky;
import static com.example.training.sportsbetting.util.OddUtils.getLatestOdd;
import static java.math.BigDecimal.ZERO;

@Builder
public class SportsBettingDemoHandler implements Handler {

    private final SportsBettingService sportsBettingService;
    private final DecimalFormat decimalFormat;
    private final Printer printer;
    private final Reader reader;

    private void printGreetings() {
        printer.println("Welcome, dear Player!");
        printer.println("It's Sports Betting Game");
        printer.println("Look, what sport events did we prepare for you!");

        printer.println();
    }

    private void printAllBetsWithPossibleOutcomes() {
        printer.println("Listing all bets with possible outcomes...");
        decimalFormat.applyPattern("###,###.##");
        decimalFormat.setGroupingUsed(false);

        for (SportEvent sportEvent : sportsBettingService.getSportEvents()) {
            printer.printf("#%s. %s", sportEvent.getIdentifier().getId(), sportEvent.getTitle());
            printer.println();

            for (Bet bet : sportEvent.getBets()) {
                printer.printf("    #%s. %s", bet.getIdentifier().getId(),
                        bet.getDescription() != null ? bet.getDescription() : bet.getType().name());
                printer.println();

                for (Outcome outcome : bet.getOutcomes()) {
                    printer.printf("        #%s. outcome: %s, odd: %s", outcome.getIdentifier().getId(),
                            outcome.getValue(), getLatestOdd(outcome.getOdds())
                                    .map(decimalFormat::format)
                                    .orElse("-"));
                    printer.println();
                }
            }
        }

        printer.println();
    }

    private void calculateWagerForUserOutcomes() {
        printer.println("Let's start the Sports Betting game!");
        printer.print("You can bet on sport events by choosing one possible outcome of a bet ");
        printer.print("and specifying the amount you wish to wager");
        printer.println();

        final List<SportEvent> possibleSportEvents = sportsBettingService.getSportEvents();
        SportEvent sportEvent = new REPLFunction<String, SportEvent>(printer, reader)
                .withLoop()
                .withMessage("Please, choose the sport's event name: ")
                .withMapper(title -> possibleSportEvents.stream()
                        .filter(event -> event.getTitle().equals(title))
                        .findFirst()
                        .orElse(null))
                .withCondition(title -> possibleSportEvents.stream()
                        .map(SportEvent::getTitle)
                        .anyMatch(title1 -> title1.equals(title)))
                .withBadMessage("Not found any sport events with title %s")
                .eval();

        final List<Bet> possibleBets = sportEvent.getBets();
        Bet bet = new REPLFunction<BetType, Bet>(printer, reader)
                .withLoop()
                .withMessage("Please, choose bet type: ")
                .withParser(BetType::valueOf)
                .withErrorMessage("Please specify bet type in proper format!")
                .withMapper(type -> possibleBets.stream()
                        .filter(bet1 -> bet1.getType().equals(type))
                        .findFirst()
                        .orElse(null))
                .withCondition(type -> possibleBets.stream()
                        .map(Bet::getType)
                        .anyMatch(type1 -> type1.equals(type)))
                .withBadMessage("Not found any bets with bet type %s")
                .eval();

        final List<Outcome> possibleOutcomes = bet.getOutcomes();
        Outcome outcome = new REPLFunction<String, Outcome>(printer, reader)
                .withLoop()
                .withMessage("Please, choose outcome: ")
                .withMapper(value -> possibleOutcomes.stream()
                        .filter(outcome1 -> outcome1.getValue().equals(value))
                        .findFirst()
                        .orElse(null))
                .withCondition(value -> possibleOutcomes.stream()
                        .map(Outcome::getValue)
                        .anyMatch(value1 -> value1.equals(value)))
                .withBadMessage("Not found any outcomes with value %s")
                .eval();

        BigDecimal odd = getLatestOdd(outcome.getOdds())
                .map(BigDecimal::valueOf)
                .orElse(ZERO);
        decimalFormat.applyPattern("###,###.##");
        printer.printf("The odd is %s", decimalFormat.format(odd));
        printer.println();

        BigDecimal betPrice = new REPLFunction<BigDecimal, BigDecimal>(printer, reader)
                .withLoop()
                .withMessage("How much do you want to bet? ")
                .withParser(sneaky((String text) -> {
                    String currencyCode = parseCurrencyCode(text);
                    decimalFormat.applyPattern("###,###.## " + currencyCode);
                    return text;
                }).andThen(sneaky((String text) -> (BigDecimal) decimalFormat.parse(text))))
                .withErrorMessage("Please, specify an amount with a 3-letter currency code!")
                .eval();

        List<Outcome> sportEventResults = sportsBettingService.getSportEventResults(sportEvent);
        if (sportEventResults.contains(outcome)) {
            printer.printf("Congrats! You won %s", decimalFormat.format(betPrice.multiply(odd)));
            printer.println();
        } else {
            printer.println("Oh! You've missed! Better luck next time, mate.");
        }

        printer.println();
    }

    private void printGoodbye() {
        printer.println("Thank you for using our app!");
    }

    @Override
    public void handle(String[] args) {
        printGreetings();
        printAllBetsWithPossibleOutcomes();
        calculateWagerForUserOutcomes();
        printGoodbye();
    }
}
