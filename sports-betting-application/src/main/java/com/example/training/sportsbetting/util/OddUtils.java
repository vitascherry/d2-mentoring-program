package com.example.training.sportsbetting.util;

import com.example.training.sportsbetting.domain.OutcomeOdd;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.Objects.requireNonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OddUtils {

    public static Optional<Double> getLatestOdd(List<OutcomeOdd> odds) {
        requireNonNull(odds, "odds must not be null");

        return odds.stream().max(comparing(OutcomeOdd::getFrom)).map(OutcomeOdd::getOdd);
    }
}
