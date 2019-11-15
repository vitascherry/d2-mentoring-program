package com.example.training.toto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder(builderClassName = "Builder")
@JsonDeserialize(builder = OutcomeSetDto.Builder.class)
public class OutcomeSetDto {

    @JsonProperty
    @NotNull
    private OutcomeDto outcome1Dto;
    @JsonProperty
    @NotNull
    private OutcomeDto outcome2Dto;
    @JsonProperty
    @NotNull
    private OutcomeDto outcome3Dto;
    @JsonProperty
    @NotNull
    private OutcomeDto outcome4Dto;
    @JsonProperty
    @NotNull
    private OutcomeDto outcome5Dto;
    @JsonProperty
    @NotNull
    private OutcomeDto outcome6Dto;
    @JsonProperty
    @NotNull
    private OutcomeDto outcome7Dto;
    @JsonProperty
    @NotNull
    private OutcomeDto outcome8Dto;
    @JsonProperty
    @NotNull
    private OutcomeDto outcome9Dto;
    @JsonProperty
    @NotNull
    private OutcomeDto outcome10Dto;
    @JsonProperty
    @NotNull
    private OutcomeDto outcome11Dto;
    @JsonProperty
    @NotNull
    private OutcomeDto outcome12Dto;
    @JsonProperty
    @NotNull
    private OutcomeDto outcome13Dto;
    @JsonProperty
    @NotNull
    private OutcomeDto outcome14Dto;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {

        private Builder() {
        }
    }
}
