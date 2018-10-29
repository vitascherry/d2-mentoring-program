package com.example.training.sportsbetting.domain;

import com.example.training.sportsbetting.domain.helper.DecimalDomainEntityIdentifier;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
import java.util.Currency;

@Data
@EqualsAndHashCode(callSuper = true)
public class Wager extends IdentifiableDomainEntity<DecimalDomainEntityIdentifier> {

    private Player player;

    private OutcomeOdd odd;

    private int amount;

    private Currency currency;

    private Instant timestamp;

    private boolean winning;

    private boolean processed;
}
