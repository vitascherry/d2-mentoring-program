package com.example.training.sportsbetting.provider;

import com.example.training.common.provider.CachedEntityProvider;
import com.example.training.common.reader.CsvReader;
import com.example.training.sportsbetting.domain.IdentifiableDomainEntity;
import com.example.training.sportsbetting.domain.Outcome;
import com.example.training.sportsbetting.domain.identifier.OutcomeCompositeKey;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@AllArgsConstructor
public class MockOutcomeProvider extends CachedEntityProvider<OutcomeCompositeKey, Outcome> {

    private static final String FILE_NAME = "csv/sport-events-bets-outcomes.csv";

    private final MockOutcomeOddProvider outcomeOddProvider;
    private final CsvReader csvReader;

    @Override
    protected Map<OutcomeCompositeKey, Outcome> initCache() {
        List<Outcome> outcomes = csvReader.getData(FILE_NAME, Outcome.class);

        outcomes.forEach(outcome -> {
            outcome.setOdds(outcomeOddProvider.getEntities()
                    .stream()
                    .filter(outcomeOdd -> outcome.getIdentifier().getEventId().equals(outcomeOdd.getIdentifier().getEventId()))
                    .filter(outcomeOdd -> outcome.getIdentifier().getBetId().equals(outcomeOdd.getIdentifier().getBetId()))
                    .filter(outcomeOdd -> outcome.getIdentifier().getId().equals(outcomeOdd.getIdentifier().getOutcomeId()))
                    .collect(toList()));
            outcome.getOdds().sort(comparing(odd -> odd.getIdentifier().getId()));
        });

        return outcomes.stream()
                .collect(toMap(IdentifiableDomainEntity::getIdentifier, x -> x));
    }
}
