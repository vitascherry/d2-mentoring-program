package com.example.training.totodemo.guice.provider;

import com.example.training.totodemo.aop.annotation.ValidateOutcomes;
import com.example.training.toto.domain.Outcome;
import com.example.training.toto.domain.OutcomeSet;
import com.google.inject.Provider;

public class OutcomeSetMapper implements Provider<OutcomeSet> {

    @ValidateOutcomes
    public OutcomeSet toOutcomeSet(Outcome[] outcomes) {
        return OutcomeSet.builder()
                .o1(outcomes[0])
                .o2(outcomes[1])
                .o3(outcomes[2])
                .o4(outcomes[3])
                .o5(outcomes[4])
                .o6(outcomes[5])
                .o7(outcomes[6])
                .o8(outcomes[7])
                .o9(outcomes[8])
                .o10(outcomes[9])
                .o11(outcomes[10])
                .o12(outcomes[11])
                .o13(outcomes[12])
                .o14(outcomes[13])
                .build();
    }

    @Override
    public OutcomeSet get() {
        throw new UnsupportedOperationException("Not implemented!");
    }
}
