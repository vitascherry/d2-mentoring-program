package com.example.training.toto.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static com.example.training.toto.constant.TotoConstants.DATE_FORMAT;

@Value
@Builder(builderClassName = "Builder")
@JsonDeserialize(builder = RoundDto.Builder.class)
public class RoundDto {

    @JsonProperty
    @Min(value = 0)
    private int year;

    @JsonProperty
    @Min(value = 0)
    private int week;

    @JsonProperty
    @Min(value = 0)
    private int round;

    @JsonProperty
    @JsonFormat(pattern = DATE_FORMAT)
    @NotNull
    private LocalDate date;

    @JsonProperty
    @Min(value = 0)
    private int numOfGames14Hits;

    @JsonProperty
    @NotNull
    private PriceDto priceOf14Hits;

    @JsonProperty
    @Min(value = 0)
    private int numOfGames13Hits;

    @JsonProperty
    @NotNull
    private PriceDto priceOf13Hits;

    @JsonProperty
    @Min(value = 0)
    private int numOfGames12Hits;

    @JsonProperty
    @NotNull
    private PriceDto priceOf12Hits;

    @JsonProperty
    @Min(value = 0)
    private int numOfGames11Hits;

    @JsonProperty
    @NotNull
    private PriceDto priceOf11Hits;

    @JsonProperty
    @Min(value = 0)
    private int numOfGames10Hits;

    @JsonProperty
    @NotNull
    private PriceDto priceOf10Hits;

    @JsonProperty("outcomes")
    @JsonUnwrapped
    @NotNull
    private OutcomeSetDto outcomeSetDto;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {

        private Builder() {
        }
    }
}
