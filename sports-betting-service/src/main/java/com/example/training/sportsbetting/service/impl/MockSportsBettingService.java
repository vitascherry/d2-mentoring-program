package com.example.training.sportsbetting.service.impl;

import com.example.training.sportsbetting.domain.Outcome;
import com.example.training.sportsbetting.domain.SportEvent;
import com.example.training.sportsbetting.repository.impl.MockSportsBettingRepository;
import com.example.training.sportsbetting.service.SportsBettingService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MockSportsBettingService implements SportsBettingService {

    private final MockSportsBettingRepository repository;

    @Override
    public List<SportEvent> getSportEvents() {
        return repository.findAllSportEvents();
    }

    @Override
    public List<Outcome> getSportEventResults(final SportEvent sportEvent) {
        return repository.findSportEventResults(sportEvent);
    }
}
