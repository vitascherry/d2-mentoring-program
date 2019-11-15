package com.example.training.toto.mapper;

import com.example.training.common.mapper.EntityMapper;
import com.example.training.toto.domain.Outcome;
import com.example.training.toto.domain.OutcomeSet;

import java.util.List;

import static java.util.Arrays.asList;

public class OutcomesEntityMapper implements EntityMapper<OutcomeSet, List<Outcome>> {

    @Override
    public List<Outcome> map(OutcomeSet entity) {
        return asList(
                entity.getO1(),
                entity.getO2(),
                entity.getO3(),
                entity.getO4(),
                entity.getO5(),
                entity.getO6(),
                entity.getO7(),
                entity.getO8(),
                entity.getO9(),
                entity.getO10(),
                entity.getO11(),
                entity.getO12(),
                entity.getO13(),
                entity.getO14()
        );
    }

    @Override
    public OutcomeSet reverse(List<Outcome> entity) {
        return OutcomeSet.builder()
                .o1(entity.get(0))
                .o2(entity.get(1))
                .o3(entity.get(2))
                .o4(entity.get(3))
                .o5(entity.get(4))
                .o6(entity.get(5))
                .o7(entity.get(6))
                .o8(entity.get(7))
                .o9(entity.get(8))
                .o10(entity.get(9))
                .o11(entity.get(10))
                .o12(entity.get(11))
                .o13(entity.get(12))
                .o14(entity.get(13))
                .build();
    }
}
