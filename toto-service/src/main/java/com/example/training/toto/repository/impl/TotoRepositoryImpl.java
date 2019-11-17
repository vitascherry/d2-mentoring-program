package com.example.training.toto.repository.impl;

import com.example.training.toto.domain.Round;
import com.example.training.toto.repository.TotoRepository;
import lombok.AllArgsConstructor;

import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Transactional
public class TotoRepositoryImpl implements TotoRepository {

    private final Provider<EntityManager> entityManagerProvider;

    @Override
    public List<Round> getAllRounds() {
        EntityManager entityManager = entityManagerProvider.get();
        return entityManager.createNamedQuery("Round.findAll", Round.class).getResultList();
    }

    @Override
    public Optional<Round> getRoundByDate(LocalDate date) {
        EntityManager entityManager = entityManagerProvider.get();
        return entityManager.createNamedQuery("Round.findByDate", Round.class)
                .setParameter("date", date)
                .getResultStream()
                .findFirst();
    }
}
