package com.example.training.common.service;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
public class DateTimeService {

    private DateTimeFormatter dateTimeFormatter;

    public String format(LocalDate date) {
        return date.format(dateTimeFormatter);
    }

    public String format(LocalDateTime dateTime) {
        return dateTime.format(dateTimeFormatter);
    }

    public LocalDate parse(String value) {
        return LocalDate.parse(value, dateTimeFormatter);
    }
}
