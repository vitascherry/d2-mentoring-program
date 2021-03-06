package com.example.training.sportsbetting.domain.identifier;

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
@JsonPropertyOrder({"eventId", "id"})
public class BetCompositeKey extends DecimalDomainEntityIdentifier {

    @JsonProperty
    private Long eventId;
}
