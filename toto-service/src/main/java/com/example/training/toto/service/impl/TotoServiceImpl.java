package com.example.training.toto.service.impl;

import com.example.training.toto.domain.*;
import com.example.training.toto.exception.RoundNotFoundException;
import com.example.training.toto.repository.TotoRepository;
import com.example.training.toto.service.TotoService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.stream.Stream;

import static com.example.training.toto.service.impl.DistributionCollector.toDistributions;
import static com.example.training.toto.util.PriceUtils.extractCurrencyOrDefault;
import static java.math.BigDecimal.ZERO;
import static java.util.Comparator.comparing;

@AllArgsConstructor
public class TotoServiceImpl implements TotoService {

    private final TotoRepository totoRepository;

    @Override
    public Price getLargestPrice() {
        final List<Round> rounds = totoRepository.getAllRounds();
        final Currency currency = rounds.stream()
                .map(round -> extractCurrencyOrDefault(round, null))
                .findFirst()
                .get();
        final Price zeroPrice = new Price(ZERO, currency);

        return Stream.of(
                rounds.stream()
                        .map(Round::getPriceOf10Hits)
                        .max(comparing(Price::getAmount, BigDecimal::compareTo))
                        .orElse(zeroPrice),
                rounds.stream()
                        .map(Round::getPriceOf11Hits)
                        .max(comparing(Price::getAmount, BigDecimal::compareTo))
                        .orElse(zeroPrice),
                rounds.stream()
                        .map(Round::getPriceOf12Hits)
                        .max(comparing(Price::getAmount, BigDecimal::compareTo))
                        .orElse(zeroPrice),
                rounds.stream()
                        .map(Round::getPriceOf13Hits)
                        .max(comparing(Price::getAmount, BigDecimal::compareTo))
                        .orElse(zeroPrice),
                rounds.stream()
                        .map(Round::getPriceOf14Hits)
                        .max(comparing(Price::getAmount, BigDecimal::compareTo))
                        .orElse(zeroPrice))
                .max(comparing(Price::getAmount, BigDecimal::compareTo))
                .orElse(zeroPrice);
    }

    @Override
    public List<Distribution> getDistributions() {
        return totoRepository.getAllRounds()
                .stream()
                .map(Round::getOutcomes)
                .map(OutcomeSet::toList)
                .collect(toDistributions());
    }

    @Override
    public Round getRound(final LocalDate date) throws RoundNotFoundException {
        return totoRepository.getRoundByDate(date)
                .orElseThrow(() -> new RoundNotFoundException(date));
    }

    @Override
    public boolean hasRound(LocalDate date) {
        return totoRepository.getRoundByDate(date).isPresent();
    }

    @Override
    public BetResult calculateWager(@NonNull Wager wager) {
        final Round round = wager.getRound();
        final Price zeroPrice = new Price(ZERO, extractCurrencyOrDefault(round, null));
        List<Outcome> realOutcomes = round.getOutcomes().toList();
        List<Outcome> wagerOutcomes = wager.getOutcomes().toList();

        BetResult betResult = new BetResult(new ArrayList<>(), zeroPrice);
        Outcome real;
        for (int i = 0; i < 14 && (real = realOutcomes.get(i)) == wagerOutcomes.get(i); i++) {
            betResult.getHits().add(Hit.builder()
                    .date(round.getDate())
                    .game(i + 1)
                    .price(i == 9 ? round.getPriceOf10Hits() :
                            i == 10 ? round.getPriceOf11Hits() :
                                    i == 11 ? round.getPriceOf12Hits() :
                                            i == 12 ? round.getPriceOf13Hits() :
                                                    i == 13 ? round.getPriceOf14Hits() : zeroPrice)
                    .round(round.getRound())
                    .outcome(real)
                    .build());
        }
        final int hits = betResult.getHits().size();
        betResult.setPrice(hits == 10 ? round.getPriceOf10Hits() :
                hits == 11 ? round.getPriceOf11Hits() :
                        hits == 12 ? round.getPriceOf12Hits() :
                                hits == 13 ? round.getPriceOf13Hits() :
                                        hits == 14 ? round.getPriceOf14Hits() : zeroPrice);

        return betResult;
    }
}
