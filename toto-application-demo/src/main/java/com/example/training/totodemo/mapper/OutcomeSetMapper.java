package com.example.training.totodemo.mapper;

import com.example.training.common.mapper.EntityMapper;
import com.example.training.toto.domain.Outcome;
import com.example.training.toto.domain.OutcomeSet;

import javax.validation.Valid;

public class OutcomeSetMapper implements EntityMapper<Outcome[], OutcomeSet> {

    @Override
    @Valid
    public OutcomeSet map(Outcome[] outcomes) {
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
}
