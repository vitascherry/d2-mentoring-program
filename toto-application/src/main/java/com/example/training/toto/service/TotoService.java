package com.example.training.toto.service;

import com.example.training.toto.domain.*;

import java.time.LocalDate;
import java.util.List;

public interface TotoService {

    Price getLargestPrice();

    List<Distribution> getDistributions();

    Round getRound(LocalDate date);

    boolean hasRound(LocalDate date);

    BetResult calculateWager(Wager wager);
}
