package com.example.training.sportsbetting.domain.identifier;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id"})
public class DecimalDomainEntityIdentifier implements DomainEntityIdentifier {

    @JsonProperty
    private Long id;
}
