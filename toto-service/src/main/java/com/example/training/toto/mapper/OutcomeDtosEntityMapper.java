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
        return OutcomeSetDto.builder()
                .outcome1Dto(entity.get(0))
                .outcome2Dto(entity.get(1))
                .outcome3Dto(entity.get(2))
                .outcome4Dto(entity.get(3))
                .outcome5Dto(entity.get(4))
                .outcome6Dto(entity.get(5))
                .outcome7Dto(entity.get(6))
                .outcome8Dto(entity.get(7))
                .outcome9Dto(entity.get(8))
                .outcome10Dto(entity.get(9))
                .outcome11Dto(entity.get(10))
                .outcome12Dto(entity.get(11))
                .outcome13Dto(entity.get(12))
                .outcome14Dto(entity.get(13))
                .build();
    }
}
