package com.example.training.sportsbetting.domain;

import com.example.training.sportsbetting.domain.identifier.DomainEntityIdentifier;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class IdentifiableDomainEntity<T extends DomainEntityIdentifier> {

    @JsonProperty(access = WRITE_ONLY)
    @JsonUnwrapped
    private T identifier;
}
