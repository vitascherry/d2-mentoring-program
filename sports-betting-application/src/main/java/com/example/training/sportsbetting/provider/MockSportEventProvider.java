package com.example.training.sportsbetting.provider;

import com.example.training.common.provider.CachedEntityProvider;
import com.example.training.common.reader.CsvReader;
import com.example.training.sportsbetting.domain.IdentifiableDomainEntity;
import com.example.training.sportsbetting.domain.SportEvent;
import com.example.training.sportsbetting.domain.helper.DomainEntityIdentifier;
import lombok.AllArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@AllArgsConstructor
public class MockSportEventProvider extends CachedEntityProvider<DomainEntityIdentifier, SportEvent> {

    private static final String FILE_NAME = "csv/sport-events.csv";

    private final MockBetProvider betProvider;
    private final CsvReader csvReader;

    @Override
    protected void initCache() {
        List<SportEvent> sportEvents = csvReader.getData(FILE_NAME, SportEvent.class);

        sportEvents.forEach(sportEvent ->
                sportEvent.setBets(betProvider.getEntities()
                        .stream()
                        .filter(bet -> sportEvent.getIdentifier().getId().equals(bet.getIdentifier().getEventId()))
                        .collect(toList())));

        cache = sportEvents.stream().collect(toMap(IdentifiableDomainEntity::getIdentifier, x -> x));
    }
}
