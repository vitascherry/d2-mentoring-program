package com.example.training.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtils {

    public static boolean isBetween(LocalDate date, LocalDate from, LocalDate to) {
        return date.compareTo(from) >= 0 && date.compareTo(to) <= 0;
    }
}
