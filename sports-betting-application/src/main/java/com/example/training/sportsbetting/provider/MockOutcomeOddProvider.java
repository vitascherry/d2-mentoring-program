package com.example.training.sportsbetting.provider;

import com.example.training.common.provider.CachedEntityProvider;
import com.example.training.common.reader.CsvReader;
import com.example.training.sportsbetting.domain.IdentifiableDomainEntity;
import com.example.training.sportsbetting.domain.OutcomeOdd;
import com.example.training.sportsbetting.domain.helper.DomainEntityIdentifier;
import lombok.AllArgsConstructor;

import static java.util.stream.Collectors.toMap;

@AllArgsConstructor
public class MockOutcomeOddProvider extends CachedEntityProvider<DomainEntityIdentifier, OutcomeOdd> {

    private static final String FILE_NAME = "csv/sport-events-bets-outcomes-odds.csv";

    private final CsvReader csvReader;

    @Override
    protected void initCache() {
        cache = csvReader.getData(FILE_NAME, OutcomeOdd.class)
                .stream()
                .collect(toMap(IdentifiableDomainEntity::getIdentifier, x -> x));
    }
}
