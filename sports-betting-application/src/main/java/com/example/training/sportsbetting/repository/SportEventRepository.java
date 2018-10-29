package com.example.training.sportsbetting.repository;

import com.example.training.sportsbetting.domain.helper.DomainEntityIdentifier;
import com.example.training.sportsbetting.domain.SportEvent;

import java.util.List;
import java.util.Optional;

public interface SportEventRepository {

    List<SportEvent> findAll();

    Optional<SportEvent> findOne(DomainEntityIdentifier id);
}
