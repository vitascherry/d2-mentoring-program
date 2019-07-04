package com.example.training.sportsbetting.provider;

import com.example.training.common.provider.CachedEntityProvider;
import com.example.training.common.provider.EntityProvider;
import com.example.training.common.reader.CsvReader;
import com.example.training.sportsbetting.domain.Bet;
import com.example.training.sportsbetting.domain.IdentifiableDomainEntity;
import com.example.training.sportsbetting.domain.Outcome;
import com.example.training.sportsbetting.domain.identifier.DomainEntityIdentifier;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@AllArgsConstructor
public class MockBetProvider extends CachedEntityProvider<DomainEntityIdentifier, Bet> {

    private static final String FILE_NAME = "csv/sport-events-bets.csv";

    private final EntityProvider<DomainEntityIdentifier, Outcome> outcomeProvider;
    private final CsvReader csvReader;

    @Override
    protected Map<DomainEntityIdentifier, Bet> initCache() {
        List<Bet> bets = csvReader.getData(FILE_NAME, Bet.class);
        final List<Outcome> outcomes = outcomeProvider.getEntities();

        bets.forEach(bet -> bet.setOutcomes(outcomes.stream()
                .filter(outcome -> bet.getIdentifier().getEventId().equals(outcome.getIdentifier().getEventId()))
                .filter(outcome -> bet.getIdentifier().getId().equals(outcome.getIdentifier().getBetId()))
                .sorted(comparing(outcome -> outcome.getIdentifier().getId()))
                .collect(toList())));

        return bets.stream()
                .collect(toMap(IdentifiableDomainEntity::getIdentifier, identity()));
    }
}
