package com.example.training.toto.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Currency;

@Value
@AllArgsConstructor
@Embeddable
public class Price {

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "CURRENCY")
    private Currency currency;

    Price() {
        this.amount = null;
        this.currency = null;
    }
}
