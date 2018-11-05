package com.example.training.sportsbetting.domain;

import com.example.training.sportsbetting.domain.helper.DecimalDomainEntityIdentifier;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Currency;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "identifier")
public class Wager extends IdentifiableDomainEntity<DecimalDomainEntityIdentifier> {

    @JsonIgnore
    private Player player;

    @JsonProperty
    private OutcomeOdd odd;

    @JsonProperty
    private int amount;

    @JsonProperty
    private Currency currency;

    @JsonProperty
    private Instant timestamp;

    @JsonProperty
    private boolean winning;

    @JsonProperty
    private boolean processed;
}
