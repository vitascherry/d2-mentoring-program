package com.example.training.toto.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static java.util.Arrays.asList;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class OutcomeSet {

    @JsonProperty
    private Outcome o1;
    @JsonProperty
    private Outcome o2;
    @JsonProperty
    private Outcome o3;
    @JsonProperty
    private Outcome o4;
    @JsonProperty
    private Outcome o5;
    @JsonProperty
    private Outcome o6;
    @JsonProperty
    private Outcome o7;
    @JsonProperty
    private Outcome o8;
    @JsonProperty
    private Outcome o9;
    @JsonProperty
    private Outcome o10;
    @JsonProperty
    private Outcome o11;
    @JsonProperty
    private Outcome o12;
    @JsonProperty
    private Outcome o13;
    @JsonProperty
    private Outcome o14;

    public OutcomeSet(Outcome[] outcomes) {
        if (outcomes.length != 14) {
            throw new IllegalArgumentException("Games count in outcomes should be 14");
        }

        o1 = outcomes[0];
        o2 = outcomes[1];
        o3 = outcomes[2];
        o4 = outcomes[3];
        o5 = outcomes[4];
        o6 = outcomes[5];
        o7 = outcomes[6];
        o8 = outcomes[7];
        o9 = outcomes[8];
        o10 = outcomes[9];
        o11 = outcomes[10];
        o12 = outcomes[11];
        o13 = outcomes[12];
        o14 = outcomes[13];
    }

    public List<Outcome> toList() {
        return asList(o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14);
    }

    @Override
    public String toString() {
        return "" + o1 + o2 + o3 + o4 + o5 + o6 + o7 + o8 + o9 + o10 + o11 + o12 + o13 + o14;
    }
}
