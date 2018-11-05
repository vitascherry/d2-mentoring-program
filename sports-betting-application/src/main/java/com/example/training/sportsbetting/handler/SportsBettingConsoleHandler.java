package com.example.training.sportsbetting.handler;

import com.example.training.common.handler.ConsoleHandler;
import com.example.training.sportsbetting.domain.Bet;
import com.example.training.sportsbetting.domain.BetType;
import com.example.training.sportsbetting.domain.Outcome;
import com.example.training.sportsbetting.domain.OutcomeOdd;
import com.example.training.sportsbetting.domain.SportEvent;
import com.example.training.sportsbetting.domain.SportEventType;
import com.example.training.sportsbetting.service.SportsBettingService;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.math.BigDecimal.ZERO;
import static java.util.Arrays.stream;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@AllArgsConstructor
public class SportsBettingConsoleHandler extends ConsoleHandler {

    private static final String SPORT_EVENT_TYPE_REGEX = stream(SportEventType.values()).map(Enum::name).collect(joining("|"));
    private static final Pattern SPORT_EVENT_TYPE_PATTERN = Pattern.compile(SPORT_EVENT_TYPE_REGEX);
    private static final String BET_TYPE_REGEX = stream(BetType.values()).map(Enum::name).collect(joining("|"));
    private static final Pattern BET_TYPE_PATTERN = Pattern.compile(BET_TYPE_REGEX);
    private static final Pattern BET_PRICE_PATTERN = Pattern.compile("(?:[\\d\\s]*)([A-Za-z]{3})");

    private final SportsBettingService sportsBettingService;
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
        for (SportEvent sportEvent : sportsBettingService.getSportEvents()) {
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

    public void calculateWagerForUserOutcomes() {
        printWithLineBreak("Let's start the Sports Betting game!");
        printWithLineBreak("You can bet on sport events by choosing one possible outcome of a bet " +
                "and specifying the amount you wish to wager");

        List<SportEvent> sportEvents = sportsBettingService.getSportEvents();
        while (true) {
            String value = readWithRetry(SPORT_EVENT_TYPE_PATTERN, "Please, choose sport event type: ",
                    String.format("You need to choose one from (%s) only!", SPORT_EVENT_TYPE_REGEX));
            final SportEventType sportEventType = SportEventType.valueOf(value);

            if (sportEvents.stream().map(SportEvent::getType).anyMatch(type -> type.equals(sportEventType))) {
                sportEvents = sportEvents.stream()
                        .filter(event -> event.getType().equals(sportEventType))
                        .collect(toList());
                break;
            }

            printWithLineBreak("Not found any sport events with type '%s'", value);
        }

        while (true) {
            final String value = readNextLine("Please, specify the name of event: ");

            if (sportEvents.stream().map(SportEvent::getTitle).anyMatch(title -> title.equalsIgnoreCase(value))) {
                sportEvents = sportEvents.stream()
                        .filter(event -> event.getTitle().equals(value))
                        .collect(toList());
                break;
            }

            printWithLineBreak("Not found any sport events with title '%s'", value);
        }

        SportEvent sportEvent = sportEvents.get(0);
        List<Bet> bets = sportEvent.getBets();
        while (true) {
            String value = readWithRetry(BET_TYPE_PATTERN, "Please, choose bet type: ",
                    String.format("You need to choose one from (%s) only!", BET_TYPE_REGEX));
            final BetType betType = BetType.valueOf(value);

            if (bets.stream().map(Bet::getType).anyMatch(type -> type.equals(betType))) {
                bets = bets.stream()
                        .filter(bet -> bet.getType().equals(betType))
                        .collect(toList());
                break;
            }

            printWithLineBreak("Not found any bets with bet type '%s'", value);
        }

        Bet bet = bets.get(0);
        List<Outcome> outcomes = bet.getOutcomes();
        while (true) {
            final String value = readNextLine("Please, choose outcome: ");

            if (outcomes.stream().map(Outcome::getValue).anyMatch(outcome -> outcome.equalsIgnoreCase(value))) {
                outcomes = outcomes.stream()
                        .filter(outcome -> outcome.getValue().equals(value))
                        .collect(toList());
                break;
            }

            printWithLineBreak("Not found any outcomes with value '%s'", value);
        }

        decimalFormatter.applyPattern("###,###.##");
        Outcome outcome = outcomes.get(0);
        BigDecimal odd = outcome.getOdds()
                .stream()
                .max(comparing(OutcomeOdd::getFrom))
                .map(OutcomeOdd::getOdd)
                .map(BigDecimal::valueOf)
                .orElse(ZERO);
        printWithLineBreak("The odd is %s", decimalFormatter.format(odd));

        String currencyCode;
        BigDecimal betPrice;
        while (true) {
            String value = readNextLine("How much do you want to bet? ");
            try {
                currencyCode = parseCurrencyCode(value);
                betPrice = (BigDecimal) decimalFormatter.parse(value);
                decimalFormatter.setCurrency(Currency.getInstance(currencyCode));
                decimalFormatter.applyPattern("###,###.## " + currencyCode);
                break;
            } catch (ParseException e) {
                printWithLineBreak("Not found currency code in text '%s'. Please, choose another one!", value);
            }

            printWithLineBreak("Please, specify an amount with a 3-letter currency code!");
        }

        List<Outcome> sportEventResults = sportsBettingService.getSportEventResults(sportEvent);
        if (sportEventResults.contains(outcome)) {
            printWithLineBreak("Congrats! You won %s", decimalFormatter.format(betPrice.multiply(odd)));
        } else {
            printWithLineBreak("Oh! You've missed! Better luck next time, mate.");
        }

        printLineBreak();
    }

    private String parseCurrencyCode(String text) throws ParseException {
        Matcher currencyCodeMatcher = BET_PRICE_PATTERN.matcher(text);
        String currencyCode;
        if (!currencyCodeMatcher.find() || (currencyCode = currencyCodeMatcher.group(1)) == null) {
            throw new ParseException(text, currencyCodeMatcher.start(1));
        }

        return currencyCode;
    }

    public void printGoodbye() {
        printWithLineBreak("Thank you for using our app!");
    }
}
