package com.example.training.toto.repository.impl;

import com.example.training.toto.domain.Round;
import com.example.training.toto.provider.RoundMockEntityProvider;
import com.example.training.toto.repository.TotoRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.example.training.common.util.DateUtils.isBetween;
import static java.util.stream.Collectors.toList;

@AllArgsConstructor
public class MockTotoRepository implements TotoRepository {

    private final RoundMockEntityProvider provider;

    @Override
    public List<Round> getAllRounds() {
        return provider.getEntities();
    }

    @Override
    public List<Round> getRoundsBetween(final LocalDate from, final LocalDate to) {
        return provider.getEntities().stream()
                .filter(round -> isBetween(round.getDate(), from, to))
                .collect(toList());
    }

    @Override
    public Optional<Round> getRoundByDate(LocalDate date) {
        return Optional.ofNullable(provider.getEntity(date));
    }
}
