package com.example.training.sportsbetting.service.impl;

import com.example.training.sportsbetting.domain.SportEvent;
import com.example.training.sportsbetting.repository.impl.MockSportEventRepository;
import com.example.training.sportsbetting.service.SportEventService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MockSportEventService implements SportEventService {

    private final MockSportEventRepository repository;

    @Override
    public List<SportEvent> getSportEvents() {
        return repository.findAll();
    }
}
