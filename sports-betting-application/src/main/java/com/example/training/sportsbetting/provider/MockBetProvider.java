package com.example.training.sportsbetting.provider;

import com.example.training.common.provider.CachedEntityProvider;
import com.example.training.common.reader.CsvReader;
import com.example.training.sportsbetting.domain.Bet;
import com.example.training.sportsbetting.domain.IdentifiableDomainEntity;
import com.example.training.sportsbetting.domain.helper.BetCompositeKey;
import lombok.AllArgsConstructor;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@AllArgsConstructor
public class MockBetProvider extends CachedEntityProvider<BetCompositeKey, Bet> {

    private static final String FILE_NAME = "csv/sport-events-bets.csv";

    private final MockOutcomeProvider outcomeProvider;
    private final CsvReader csvReader;

    @Override
    protected void initCache() {
        List<Bet> bets = csvReader.getData(FILE_NAME, Bet.class);

        bets.forEach(bet -> {
            bet.setOutcomes(outcomeProvider.getEntities()
                    .stream()
                    .filter(outcome -> bet.getIdentifier().getEventId().equals(outcome.getIdentifier().getEventId()))
                    .filter(outcome -> bet.getIdentifier().getId().equals(outcome.getIdentifier().getBetId()))
                    .collect(toList()));
            bet.getOutcomes().sort(comparing(outcome -> outcome.getIdentifier().getId()));
        });

        cache = bets.stream()
                .collect(toMap(IdentifiableDomainEntity::getIdentifier, x -> x));
    }
}
