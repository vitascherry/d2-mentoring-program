package com.example.training.sportsbetting.provider;

import com.example.training.common.provider.CachedEntityProvider;
import com.example.training.common.reader.CsvReader;
import com.example.training.sportsbetting.domain.IdentifiableDomainEntity;
import com.example.training.sportsbetting.domain.SportEvent;
import com.example.training.sportsbetting.domain.helper.DecimalDomainEntityIdentifier;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@AllArgsConstructor
public class MockSportEventProvider extends CachedEntityProvider<DecimalDomainEntityIdentifier, SportEvent> {

    private static final String FILE_NAME = "csv/sport-events.csv";

    private final MockBetProvider betProvider;
    private final CsvReader csvReader;

    @Override
    protected Map<DecimalDomainEntityIdentifier, SportEvent> initCache() {
        List<SportEvent> sportEvents = csvReader.getData(FILE_NAME, SportEvent.class);

        sportEvents.forEach(sportEvent -> {
            sportEvent.setBets(betProvider.getEntities()
                    .stream()
                    .filter(bet -> sportEvent.getIdentifier().getId().equals(bet.getIdentifier().getEventId()))
                    .collect(toList()));
            sportEvent.getBets().sort(comparing(bet -> bet.getIdentifier().getId()));
        });

        return sportEvents.stream()
                .collect(toMap(IdentifiableDomainEntity::getIdentifier, x -> x));
    }
}
