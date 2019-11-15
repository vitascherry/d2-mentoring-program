package com.example.training.toto.mapper;

import com.example.training.common.mapper.EntityMapper;
import com.example.training.toto.domain.Outcome;
import com.example.training.toto.dto.OutcomeDto;

public class OutcomeEntityMapper implements EntityMapper<Outcome, OutcomeDto> {

    @Override
    public OutcomeDto map(Outcome entity) {
        return OutcomeDto.fromValue(entity.getValue());
    }

    @Override
    public Outcome reverse(OutcomeDto entity) {
        return Outcome.fromValue(entity.getValue());
    }
}
