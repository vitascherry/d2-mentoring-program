package com.example.training.toto.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RoundNotFoundException extends Exception {

    private final LocalDate date;
}
