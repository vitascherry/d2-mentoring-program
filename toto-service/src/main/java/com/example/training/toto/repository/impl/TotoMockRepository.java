package com.example.training.toto.repository.impl;

import com.example.training.common.provider.EntityProvider;
import com.example.training.toto.domain.Round;
import com.example.training.toto.repository.TotoRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class TotoMockRepository implements TotoRepository {

    private final EntityProvider<LocalDate, Round> provider;

    @Override
    public List<Round> getAllRounds() {
        return provider.getEntities();
    }

    @Override
    public Optional<Round> getRoundByDate(LocalDate date) {
        return Optional.ofNullable(provider.getEntity(date));
    }
}
