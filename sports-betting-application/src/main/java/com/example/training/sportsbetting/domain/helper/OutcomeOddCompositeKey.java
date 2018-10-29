package com.example.training.sportsbetting.domain.helper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"eventId", "betId", "outcomeId", "id"})
public class OutcomeOddCompositeKey extends OutcomeCompositeKey {

    @JsonProperty
    private Long outcomeId;
}
