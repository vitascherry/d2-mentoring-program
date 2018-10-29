package com.example.training.sportsbetting.domain;

import com.example.training.sportsbetting.domain.helper.OutcomeCompositeKey;
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
public class Outcome extends IdentifiableDomainEntity<OutcomeCompositeKey> {

    @JsonIgnore
    private Bet bet;

    @JsonProperty
    private String value;

    @JsonProperty
    private List<OutcomeOdd> odds;
}
