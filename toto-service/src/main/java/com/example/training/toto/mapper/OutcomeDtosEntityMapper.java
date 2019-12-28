package com.example.training.toto.mapper;

import com.example.training.common.mapper.EntityMapper;
import com.example.training.toto.dto.OutcomeDto;
import com.example.training.toto.dto.OutcomeSetDto;

import java.util.List;

import static java.util.Arrays.asList;

public class OutcomeDtosEntityMapper implements EntityMapper<OutcomeSetDto, List<OutcomeDto>> {

    @Override
    public List<OutcomeDto> map(OutcomeSetDto entity) {
        return asList(
                entity.getOutcome1Dto(),
                entity.getOutcome2Dto(),
                entity.getOutcome3Dto(),
                entity.getOutcome4Dto(),
                entity.getOutcome5Dto(),
                entity.getOutcome6Dto(),
                entity.getOutcome7Dto(),
                entity.getOutcome8Dto(),
                entity.getOutcome9Dto(),
                entity.getOutcome10Dto(),
                entity.getOutcome11Dto(),
                entity.getOutcome12Dto(),
                entity.getOutcome13Dto(),
                entity.getOutcome14Dto()
        );
    }

    @Override
    public OutcomeSetDto reverse(List<OutcomeDto> entity) {
        throw new UnsupportedOperationException("Not Implemented");
    }
}
