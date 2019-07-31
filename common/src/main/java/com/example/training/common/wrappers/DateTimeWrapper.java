package com.example.training.common.wrappers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.Clock.systemUTC;

public class DateTimeWrapper {

    public LocalDate currentDate() {
        return LocalDate.now(systemUTC());
    }

    public LocalDateTime currentDateTime() {
        return LocalDateTime.now(systemUTC());
    }
}
