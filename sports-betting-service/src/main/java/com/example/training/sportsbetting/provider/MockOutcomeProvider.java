package com.example.training.sportsbetting.provider;

import com.example.training.common.provider.CachedEntityProvider;
import com.example.training.common.provider.EntityProvider;
import com.example.training.common.reader.CsvReader;
import com.example.training.sportsbetting.domain.IdentifiableDomainEntity;
import com.example.training.sportsbetting.domain.Outcome;
import com.example.training.sportsbetting.domain.OutcomeOdd;
import com.example.training.sportsbetting.domain.identifier.DomainEntityIdentifier;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@AllArgsConstructor
public class MockOutcomeProvider extends CachedEntityProvider<DomainEntityIdentifier, Outcome> {

    private static final String FILE_NAME = "csv/sport-events-bets-outcomes.csv";

    private final EntityProvider<DomainEntityIdentifier, OutcomeOdd> outcomeOddProvider;
    private final CsvReader csvReader;

    @Override
    protected Map<DomainEntityIdentifier, Outcome> initCache() {
        List<Outcome> outcomes = csvReader.getData(FILE_NAME, Outcome.class);
        final List<OutcomeOdd> outcomeOdds = outcomeOddProvider.getEntities();

        outcomes.forEach(outcome -> outcome.setOdds(outcomeOdds.stream()
                .filter(odd -> outcome.getIdentifier().getEventId().equals(odd.getIdentifier().getEventId()))
                .filter(odd -> outcome.getIdentifier().getBetId().equals(odd.getIdentifier().getBetId()))
                .filter(odd -> outcome.getIdentifier().getId().equals(odd.getIdentifier().getOutcomeId()))
                .sorted(comparing(odd -> odd.getIdentifier().getId()))
                .collect(toList())));

        return outcomes.stream()
                .collect(toMap(IdentifiableDomainEntity::getIdentifier, identity()));
    }
}
