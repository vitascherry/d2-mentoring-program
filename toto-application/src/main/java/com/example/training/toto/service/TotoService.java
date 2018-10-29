package com.example.training.toto.service;

import com.example.training.toto.domain.*;
import com.example.training.toto.exception.RoundNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface TotoService {

    Price getLargestPrice();

    List<Distribution> getDistributions();

    Round getRoundByDate(LocalDate date) throws RoundNotFoundException;

    BetResult calculateWager(Wager wager);
}
