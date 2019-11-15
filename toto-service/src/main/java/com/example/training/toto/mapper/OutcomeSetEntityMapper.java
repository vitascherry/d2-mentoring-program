package com.example.training.toto.mapper;

import com.example.training.common.mapper.EntityMapper;
import com.example.training.toto.domain.Outcome;
import com.example.training.toto.domain.OutcomeSet;
import com.example.training.toto.dto.OutcomeDto;
import com.example.training.toto.dto.OutcomeSetDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OutcomeSetEntityMapper implements EntityMapper<OutcomeSet, OutcomeSetDto> {

    private final EntityMapper<Outcome, OutcomeDto> outcomeEntityMapper;

    @Override
    public OutcomeSetDto map(OutcomeSet entity) {
        return OutcomeSetDto.builder()
                .outcome1Dto(outcomeEntityMapper.map(entity.getO1()))
                .outcome2Dto(outcomeEntityMapper.map(entity.getO2()))
                .outcome3Dto(outcomeEntityMapper.map(entity.getO3()))
                .outcome4Dto(outcomeEntityMapper.map(entity.getO4()))
                .outcome5Dto(outcomeEntityMapper.map(entity.getO5()))
                .outcome6Dto(outcomeEntityMapper.map(entity.getO6()))
                .outcome7Dto(outcomeEntityMapper.map(entity.getO7()))
                .outcome8Dto(outcomeEntityMapper.map(entity.getO8()))
                .outcome9Dto(outcomeEntityMapper.map(entity.getO9()))
                .outcome10Dto(outcomeEntityMapper.map(entity.getO10()))
                .outcome11Dto(outcomeEntityMapper.map(entity.getO11()))
                .outcome12Dto(outcomeEntityMapper.map(entity.getO12()))
                .outcome13Dto(outcomeEntityMapper.map(entity.getO13()))
                .outcome14Dto(outcomeEntityMapper.map(entity.getO14()))
                .build();
    }

    @Override
    public OutcomeSet reverse(OutcomeSetDto entity) {
        return OutcomeSet.builder()
                .o1(outcomeEntityMapper.reverse(entity.getOutcome1Dto()))
                .o2(outcomeEntityMapper.reverse(entity.getOutcome2Dto()))
                .o3(outcomeEntityMapper.reverse(entity.getOutcome3Dto()))
                .o4(outcomeEntityMapper.reverse(entity.getOutcome4Dto()))
                .o5(outcomeEntityMapper.reverse(entity.getOutcome5Dto()))
                .o6(outcomeEntityMapper.reverse(entity.getOutcome6Dto()))
                .o7(outcomeEntityMapper.reverse(entity.getOutcome7Dto()))
                .o8(outcomeEntityMapper.reverse(entity.getOutcome8Dto()))
                .o9(outcomeEntityMapper.reverse(entity.getOutcome9Dto()))
                .o10(outcomeEntityMapper.reverse(entity.getOutcome10Dto()))
                .o11(outcomeEntityMapper.reverse(entity.getOutcome11Dto()))
                .o12(outcomeEntityMapper.reverse(entity.getOutcome12Dto()))
                .o13(outcomeEntityMapper.reverse(entity.getOutcome13Dto()))
                .o14(outcomeEntityMapper.reverse(entity.getOutcome14Dto()))
                .build();
    }
}
