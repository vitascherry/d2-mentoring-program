package com.example.training.sportsbetting.repository.impl;

import com.example.training.common.provider.EntityProvider;
import com.example.training.sportsbetting.domain.Outcome;
import com.example.training.sportsbetting.domain.SportEvent;
import com.example.training.sportsbetting.domain.identifier.DomainEntityIdentifier;
import com.example.training.sportsbetting.repository.SportsBettingRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@AllArgsConstructor
public class SportsBettingRepositoryImpl implements SportsBettingRepository {

    private final EntityProvider<DomainEntityIdentifier, SportEvent> sportEventProvider;
    private final EntityProvider<DomainEntityIdentifier, List<Outcome>> resultsProvider;

    @Override
    public List<SportEvent> findAllSportEvents() {
        return sportEventProvider.getEntities();
    }

    @Override
    public List<Outcome> findSportEventResults(@NonNull DomainEntityIdentifier identifier) {
        return Optional.ofNullable(resultsProvider.getEntity(identifier)).orElse(emptyList());
    }
}
