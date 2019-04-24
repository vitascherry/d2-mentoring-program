package com.example.training.common.wrappers;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateTimeWrapper {

    public LocalDate currentDate() {
        return LocalDate.now(Clock.systemUTC());
    }

    public LocalDateTime currentDateTime() {
        return LocalDateTime.now(Clock.systemUTC());
    }
}
