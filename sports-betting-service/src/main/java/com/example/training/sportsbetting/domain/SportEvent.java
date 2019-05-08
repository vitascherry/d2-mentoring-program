package com.example.training.sportsbetting.domain;

import com.example.training.sportsbetting.domain.identifier.DecimalDomainEntityIdentifier;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.training.sportsbetting.constant.SportsBettingConstants.DATE_TIME_FORMAT;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "identifier")
public class SportEvent extends IdentifiableDomainEntity<DecimalDomainEntityIdentifier> {

    @JsonProperty
    private String name;

    @JsonProperty
    private SportEventType type;

    @JsonProperty
    private String title;

    @JsonProperty
    @JsonFormat(pattern = DATE_TIME_FORMAT)
    private LocalDateTime start;

    @JsonProperty
    @JsonFormat(pattern = DATE_TIME_FORMAT)
    private LocalDateTime end;

    @JsonProperty
    private List<Bet> bets;
}
