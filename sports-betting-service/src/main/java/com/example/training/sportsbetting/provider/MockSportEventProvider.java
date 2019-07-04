package com.example.training.sportsbetting.provider;

import com.example.training.common.provider.CachedEntityProvider;
import com.example.training.common.provider.EntityProvider;
import com.example.training.common.reader.CsvReader;
import com.example.training.sportsbetting.domain.Bet;
import com.example.training.sportsbetting.domain.IdentifiableDomainEntity;
import com.example.training.sportsbetting.domain.SportEvent;
import com.example.training.sportsbetting.domain.identifier.DomainEntityIdentifier;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@AllArgsConstructor
public class MockSportEventProvider extends CachedEntityProvider<DomainEntityIdentifier, SportEvent> {

    private static final String FILE_NAME = "csv/sport-events.csv";

    private final EntityProvider<DomainEntityIdentifier, Bet> betProvider;
    private final CsvReader csvReader;

    @Override
    protected Map<DomainEntityIdentifier, SportEvent> initCache() {
        List<SportEvent> sportEvents = csvReader.getData(FILE_NAME, SportEvent.class);
        final List<Bet> bets = betProvider.getEntities();

        sportEvents.forEach(sportEvent -> sportEvent.setBets(bets.stream()
                .filter(bet -> sportEvent.getIdentifier().getId().equals(bet.getIdentifier().getEventId()))
                .sorted(comparing(bet -> bet.getIdentifier().getId()))
                .collect(toList())));

        return sportEvents.stream()
                .collect(toMap(IdentifiableDomainEntity::getIdentifier, identity()));
    }
}
