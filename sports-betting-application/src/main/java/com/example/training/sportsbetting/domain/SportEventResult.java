package com.example.training.sportsbetting.domain;

import com.example.training.sportsbetting.domain.helper.DecimalDomainEntityIdentifier;
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
public class SportEventResult extends IdentifiableDomainEntity<DecimalDomainEntityIdentifier> {

    @JsonIgnore
    private SportEvent event;

    @JsonProperty
    private List<Outcome> outcomes;
}
