package com.example.training.toto.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

import static java.util.Arrays.asList;

@Getter
@EqualsAndHashCode
@JsonDeserialize(builder = OutcomeSet.Builder.class)
public class OutcomeSet {

    @JsonProperty
    private final Outcome o1;
    @JsonProperty
    private final Outcome o2;
    @JsonProperty
    private final Outcome o3;
    @JsonProperty
    private final Outcome o4;
    @JsonProperty
    private final Outcome o5;
    @JsonProperty
    private final Outcome o6;
    @JsonProperty
    private final Outcome o7;
    @JsonProperty
    private final Outcome o8;
    @JsonProperty
    private final Outcome o9;
    @JsonProperty
    private final Outcome o10;
    @JsonProperty
    private final Outcome o11;
    @JsonProperty
    private final Outcome o12;
    @JsonProperty
    private final Outcome o13;
    @JsonProperty
    private final Outcome o14;

    private OutcomeSet(Builder builder) {
        this.o1 = builder.o1;
        this.o2 = builder.o2;
        this.o3 = builder.o3;
        this.o4 = builder.o4;
        this.o5 = builder.o5;
        this.o6 = builder.o6;
        this.o7 = builder.o7;
        this.o8 = builder.o8;
        this.o9 = builder.o9;
        this.o10 = builder.o10;
        this.o11 = builder.o11;
        this.o12 = builder.o12;
        this.o13 = builder.o13;
        this.o14 = builder.o14;
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<Outcome> toList() {
        return asList(o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14);
    }

    @Override
    public String toString() {
        return "" + o1 + o2 + o3 + o4 + o5 + o6 + o7 + o8 + o9 + o10 + o11 + o12 + o13 + o14;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {

        private Outcome o1;
        private Outcome o2;
        private Outcome o3;
        private Outcome o4;
        private Outcome o5;
        private Outcome o6;
        private Outcome o7;
        private Outcome o8;
        private Outcome o9;
        private Outcome o10;
        private Outcome o11;
        private Outcome o12;
        private Outcome o13;
        private Outcome o14;

        private Builder() {
        }

        public Builder o1(Outcome o1) {
            this.o1 = o1;
            return this;
        }

        public Builder o2(Outcome o2) {
            this.o2 = o2;
            return this;
        }

        public Builder o3(Outcome o3) {
            this.o3 = o3;
            return this;
        }

        public Builder o4(Outcome o4) {
            this.o4 = o4;
            return this;
        }

        public Builder o5(Outcome o5) {
            this.o5 = o5;
            return this;
        }

        public Builder o6(Outcome o6) {
            this.o6 = o6;
            return this;
        }

        public Builder o7(Outcome o7) {
            this.o7 = o7;
            return this;
        }

        public Builder o8(Outcome o8) {
            this.o8 = o8;
            return this;
        }

        public Builder o9(Outcome o9) {
            this.o9 = o9;
            return this;
        }

        public Builder o10(Outcome o10) {
            this.o10 = o10;
            return this;
        }

        public Builder o11(Outcome o11) {
            this.o11 = o11;
            return this;
        }

        public Builder o12(Outcome o12) {
            this.o12 = o12;
            return this;
        }

        public Builder o13(Outcome o13) {
            this.o13 = o13;
            return this;
        }

        public Builder o14(Outcome o14) {
            this.o14 = o14;
            return this;
        }

        public OutcomeSet build() {
            return new OutcomeSet(this);
        }
    }
}
