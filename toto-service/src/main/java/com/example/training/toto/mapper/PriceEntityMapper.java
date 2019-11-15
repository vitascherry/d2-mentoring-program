package com.example.training.toto.mapper;

import com.example.training.common.mapper.EntityMapper;
import com.example.training.toto.domain.Price;
import com.example.training.toto.dto.PriceDto;

public class PriceEntityMapper implements EntityMapper<Price, PriceDto> {

    @Override
    public PriceDto map(Price entity) {
        return new PriceDto(entity.getAmount(), entity.getCurrency());
    }

    @Override
    public Price reverse(PriceDto entity) {
        return new Price(entity.getAmount(), entity.getCurrency());
    }
}
