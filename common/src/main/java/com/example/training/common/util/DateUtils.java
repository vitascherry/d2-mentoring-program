package com.example.training.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtils {

    public static boolean isBetween(@NonNull LocalDate date, @NonNull LocalDate from, @NonNull LocalDate to) {
        return date.compareTo(from) >= 0 && date.compareTo(to) <= 0;
    }
}
