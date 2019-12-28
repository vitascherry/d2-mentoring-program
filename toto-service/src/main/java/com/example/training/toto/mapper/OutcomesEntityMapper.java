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
        throw new UnsupportedOperationException("Not Implemented");
    }
}
