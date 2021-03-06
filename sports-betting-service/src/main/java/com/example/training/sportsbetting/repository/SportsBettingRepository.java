package com.example.training.sportsbetting.repository;

import com.example.training.sportsbetting.domain.Outcome;
import com.example.training.sportsbetting.domain.SportEvent;
import com.example.training.sportsbetting.domain.identifier.DomainEntityIdentifier;

import java.util.List;

public interface SportsBettingRepository {

    List<SportEvent> findAllSportEvents();

    List<Outcome> findSportEventResults(DomainEntityIdentifier identifier);
}
