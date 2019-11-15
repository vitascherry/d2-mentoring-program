package com.example.training.totodemo.mapper;

import com.example.training.common.mapper.EntityMapper;
import com.example.training.toto.dto.OutcomeDto;
import com.example.training.toto.dto.OutcomeSetDto;

import javax.validation.Valid;

public class OutcomeSetDtoMapper implements EntityMapper<OutcomeDto[], OutcomeSetDto> {

    @Override
    @Valid
    public OutcomeSetDto map(OutcomeDto[] outcomes) {
        return OutcomeSetDto.builder()
                .outcome1Dto(outcomes[0])
                .outcome2Dto(outcomes[1])
                .outcome3Dto(outcomes[2])
                .outcome4Dto(outcomes[3])
                .outcome5Dto(outcomes[4])
                .outcome6Dto(outcomes[5])
                .outcome7Dto(outcomes[6])
                .outcome8Dto(outcomes[7])
                .outcome9Dto(outcomes[8])
                .outcome10Dto(outcomes[9])
                .outcome11Dto(outcomes[10])
                .outcome12Dto(outcomes[11])
                .outcome13Dto(outcomes[12])
                .outcome14Dto(outcomes[13])
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
