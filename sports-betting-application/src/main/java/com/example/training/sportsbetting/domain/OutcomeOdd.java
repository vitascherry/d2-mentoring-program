package com.example.training.sportsbetting.domain;

import com.example.training.sportsbetting.domain.helper.OutcomeOddCompositeKey;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.example.training.sportsbetting.constant.SportsBettingConstants.DATE_TIME_FORMAT;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "identifier")
public class OutcomeOdd extends IdentifiableDomainEntity<OutcomeOddCompositeKey> {

    @JsonIgnore
    private Outcome outcome;

    @JsonProperty
    @JsonFormat(pattern = DATE_TIME_FORMAT)
    private LocalDateTime from;

    @JsonProperty
    @JsonFormat(pattern = DATE_TIME_FORMAT)
    private LocalDateTime to;

    @JsonProperty
    private Double odd;
}
