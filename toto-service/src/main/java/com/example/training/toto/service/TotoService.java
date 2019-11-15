package com.example.training.toto.service;

import com.example.training.toto.dto.BetResultDto;
import com.example.training.toto.dto.DistributionDto;
import com.example.training.toto.dto.WagerDto;
import com.example.training.toto.dto.PriceDto;
import com.example.training.toto.dto.RoundDto;
import com.example.training.toto.exception.RoundNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface TotoService {

    PriceDto getLargestPrice();

    List<DistributionDto> getDistributions();

    RoundDto getRound(LocalDate date) throws RoundNotFoundException;

    boolean hasRound(LocalDate date);

    BetResultDto calculateWager(WagerDto wager);
}
