package com.example.training.toto.mapper;

import com.example.training.common.mapper.EntityMapper;
import com.example.training.toto.domain.Distribution;
import com.example.training.toto.dto.DistributionDto;

public class DistributionEntityMapper implements EntityMapper<Distribution, DistributionDto> {

    @Override
    public DistributionDto map(Distribution entity) {
        return new DistributionDto(entity.getFirst(), entity.getSecond(), entity.getDraw());
    }

    @Override
    public Distribution reverse(DistributionDto entity) {
        return Distribution.builder()
                .first(entity.getFirst())
                .second(entity.getSecond())
                .draw(entity.getDraw())
                .build();
    }
}
