package com.example.training.toto.repository.impl;

import com.example.training.toto.domain.Round;
import com.example.training.toto.repository.TotoRepository;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Transactional
public class TotoRepositoryImpl implements TotoRepository {

    private final EntityManager entityManager;

    @Override
    public List<Round> getAllRounds() {
        return entityManager.createNamedQuery("Round.findAll", Round.class).getResultList();
    }

    @Override
    public Optional<Round> getRoundByDate(LocalDate date) {
        return entityManager.createNamedQuery("Round.findByDate", Round.class)
                .setParameter("date", date)
                .getResultStream()
                .findFirst();
    }
}
