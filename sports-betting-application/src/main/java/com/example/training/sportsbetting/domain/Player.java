package com.example.training.sportsbetting.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Player extends User {

    private String name;

    private String accountNumber;

    private int balance;

    private Currency currency;

    private LocalDate dateOfBirth;

    private List<Wager> wagers;
}
