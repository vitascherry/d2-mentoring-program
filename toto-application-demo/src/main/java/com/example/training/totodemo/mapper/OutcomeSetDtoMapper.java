package com.example.training.totodemo.mapper;

import com.example.training.common.mapper.EntityMapper;
import com.example.training.toto.dto.OutcomeDto;
import com.example.training.toto.dto.OutcomeSetDto;

import javax.validation.Valid;

public class OutcomeSetDtoMapper implements EntityMapper<OutcomeDto[], OutcomeSetDto> {

    @Override
    @Valid
    public OutcomeSetDto map(OutcomeDto[] outcomeDtos) {
        if (outcomeDtos.length != 14) {
            throw new IllegalArgumentException("Outcomes array size must be 14!");
        }
        return OutcomeSetDto.builder()
                .outcome1Dto(outcomeDtos[0])
                .outcome2Dto(outcomeDtos[1])
                .outcome3Dto(outcomeDtos[2])
                .outcome4Dto(outcomeDtos[3])
                .outcome5Dto(outcomeDtos[4])
                .outcome6Dto(outcomeDtos[5])
                .outcome7Dto(outcomeDtos[6])
                .outcome8Dto(outcomeDtos[7])
                .outcome9Dto(outcomeDtos[8])
                .outcome10Dto(outcomeDtos[9])
                .outcome11Dto(outcomeDtos[10])
                .outcome12Dto(outcomeDtos[11])
                .outcome13Dto(outcomeDtos[12])
                .outcome14Dto(outcomeDtos[13])
                .build();
    }

    @Override
    public OutcomeDto[] reverse(OutcomeSetDto entity) {
        return new OutcomeDto[]{
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
        };
    }


}
