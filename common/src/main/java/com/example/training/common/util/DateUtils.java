package com.example.training.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static java.util.Objects.requireNonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtils {

    public static boolean isBetween(LocalDate date, LocalDate from, LocalDate to) {
        requireNonNull(date, "date must not be null");
        requireNonNull(from, "from date must not be null");
        requireNonNull(from, "to date must not be null");

        return date.compareTo(from) >= 0 && date.compareTo(to) <= 0;
    }
}
