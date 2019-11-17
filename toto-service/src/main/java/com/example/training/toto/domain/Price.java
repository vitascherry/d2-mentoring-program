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

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @Column(name = "CURRENCY", nullable = false)
    private Currency currency;

    private Price() {
        this.amount = null;
        this.currency = null;
    }
}
