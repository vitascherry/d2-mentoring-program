package com.example.training.toto.repository;

import com.example.training.toto.domain.Round;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TotoRepository {

    List<Round> getAllRounds();

    Optional<Round> getRoundByDate(LocalDate date);
}
