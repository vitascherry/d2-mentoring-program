package com.example.training.toto.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

import java.time.LocalDate;

import static com.example.training.toto.constant.TotoConstants.DATE_FORMAT;

@Data
public class Round {

    @JsonProperty
    private int year;

    @JsonProperty
    private int week;

    @JsonProperty
    private int round;

    @JsonProperty
    @JsonFormat(pattern = DATE_FORMAT)
    private LocalDate date;

    @JsonProperty
    private int numOfGames14Hits;

    @JsonProperty
    private Price priceOf14Hits;

    @JsonProperty
    private int numOfGames13Hits;

    @JsonProperty
    private Price priceOf13Hits;

    @JsonProperty
    private int numOfGames12Hits;

    @JsonProperty
    private Price priceOf12Hits;

    @JsonProperty
    private int numOfGames11Hits;

    @JsonProperty
    private Price priceOf11Hits;

    @JsonProperty
    private int numOfGames10Hits;

    @JsonProperty
    private Price priceOf10Hits;

    @JsonProperty
    @JsonUnwrapped
    private OutcomeSet outcomes;

    public void setDate(LocalDate date) {
        if (date != null) {
            this.date = date;
        } else {
            this.date = LocalDate.of(year, 1, 1).plusWeeks((long) week);
        }
    }
}
