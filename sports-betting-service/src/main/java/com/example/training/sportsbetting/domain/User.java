package com.example.training.sportsbetting.domain;

import com.example.training.sportsbetting.domain.identifier.DecimalDomainEntityIdentifier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "identifier")
public class User extends IdentifiableDomainEntity<DecimalDomainEntityIdentifier> {

    private String email;

    private String passwordHash;

    private boolean active;
}
