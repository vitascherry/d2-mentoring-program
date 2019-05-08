package com.example.training.sportsbetting.domain;

import com.example.training.sportsbetting.domain.identifier.BetCompositeKey;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "identifier")
public class Bet extends IdentifiableDomainEntity<BetCompositeKey> {

    @JsonIgnore
    private SportEvent sportEvent;

    @JsonProperty
    private BetType type;

    @JsonProperty
    private String description;

    @JsonProperty
    private List<Outcome> outcomes;
}
