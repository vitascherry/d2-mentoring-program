package com.example.training.common.service;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.requireNonNull;

@AllArgsConstructor
public class DateTimeService {

    private DateTimeFormatter dateTimeFormatter;

    public String format(LocalDate date) {
        requireNonNull(date, "date must not be null");

        return date.format(dateTimeFormatter);
    }

    public String format(LocalDateTime dateTime) {
        requireNonNull(dateTime, "dateTime must not be null");

        return dateTime.format(dateTimeFormatter);
    }

    public LocalDate parse(String value) {
        return LocalDate.parse(value, dateTimeFormatter);
    }
}
