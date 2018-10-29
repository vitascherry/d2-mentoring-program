package com.example.training.sportsbetting.domain;

import com.example.training.sportsbetting.domain.helper.DecimalDomainEntityIdentifier;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class User extends IdentifiableDomainEntity<DecimalDomainEntityIdentifier> {

    private String email;

    private String passwordHash;

    private boolean active;
}
