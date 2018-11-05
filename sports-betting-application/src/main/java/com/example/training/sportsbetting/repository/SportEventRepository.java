package com.example.training.sportsbetting.repository;

import com.example.training.sportsbetting.domain.SportEvent;

import java.util.List;

public interface SportEventRepository {

    List<SportEvent> findAll();
}
