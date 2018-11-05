package com.example.training.sportsbetting.repository.impl;

import com.example.training.sportsbetting.domain.Outcome;
import com.example.training.sportsbetting.domain.SportEvent;
import com.example.training.sportsbetting.provider.MockSportEventProvider;
import com.example.training.sportsbetting.provider.MockSportEventResultsProvider;
import com.example.training.sportsbetting.repository.SportsBettingRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MockSportsBettingRepository implements SportsBettingRepository {

    private final MockSportEventProvider sportEventProvider;
    private final MockSportEventResultsProvider resultsProvider;

    @Override
    public List<SportEvent> findAllSportEvents() {
        return sportEventProvider.getEntities();
    }

    @Override
    public List<Outcome> findSportEventResults(SportEvent sportEvent) {
        return resultsProvider.getEntity(sportEvent.getIdentifier());
    }
}
