package com.example.training.sportsbetting.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Currency;

@EqualsAndHashCode(callSuper = true)
@Data
public class Player extends User {

    private String name;

    private String accountNumber;

    private int balance;

    private Currency currency;

    private LocalDate dateOfBirth;
}
