package com.example.training.sportsbetting.service;

import com.example.training.sportsbetting.domain.Outcome;
import com.example.training.sportsbetting.domain.SportEvent;

import java.util.List;

public interface SportsBettingService {

    List<SportEvent> getSportEvents();

    List<Outcome> getSportEventResults(SportEvent sportEvent);
}
