package com.example.training.sportsbetting.util;

import com.example.training.sportsbetting.domain.OutcomeOdd;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OddUtils {

    public static Optional<Double> getLatestOdd(@NonNull List<OutcomeOdd> odds) {
        return odds.stream().max(comparing(OutcomeOdd::getFrom)).map(OutcomeOdd::getOdd);
    }
}
