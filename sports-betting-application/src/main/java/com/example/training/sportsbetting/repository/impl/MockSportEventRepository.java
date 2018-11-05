package com.example.training.sportsbetting.repository.impl;

import com.example.training.sportsbetting.domain.SportEvent;
import com.example.training.sportsbetting.provider.MockSportEventProvider;
import com.example.training.sportsbetting.repository.SportEventRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MockSportEventRepository implements SportEventRepository {

    private final MockSportEventProvider sportEventProvider;

    @Override
    public List<SportEvent> findAll() {
        return sportEventProvider.getEntities();
    }
}
