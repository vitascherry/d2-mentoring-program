package com.example.training.toto.mapper;

import com.example.training.common.mapper.EntityMapper;
import com.example.training.toto.domain.OutcomeSet;
import com.example.training.toto.domain.Price;
import com.example.training.toto.domain.Round;
import com.example.training.toto.dto.OutcomeSetDto;
import com.example.training.toto.dto.PriceDto;
import com.example.training.toto.dto.RoundDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoundEntityMapper implements EntityMapper<Round, RoundDto> {

    private final EntityMapper<OutcomeSet, OutcomeSetDto> outcomeSetEntityMapper;
    private final EntityMapper<Price, PriceDto> priceEntityMapper;

    @Override
    public RoundDto map(Round entity) {
        return RoundDto.builder()
                .date(entity.getDate())
                .week(entity.getWeek())
                .year(entity.getYear())
                .round(entity.getRound())
                .outcomeSetDto(outcomeSetEntityMapper.map(entity.getOutcomes()))
                .numOfGames10Hits(entity.getNumOfGames10Hits())
                .numOfGames11Hits(entity.getNumOfGames11Hits())
                .numOfGames12Hits(entity.getNumOfGames12Hits())
                .numOfGames13Hits(entity.getNumOfGames13Hits())
                .numOfGames14Hits(entity.getNumOfGames14Hits())
                .priceOf10Hits(priceEntityMapper.map(entity.getPriceOf10Hits()))
                .priceOf11Hits(priceEntityMapper.map(entity.getPriceOf11Hits()))
                .priceOf12Hits(priceEntityMapper.map(entity.getPriceOf12Hits()))
                .priceOf13Hits(priceEntityMapper.map(entity.getPriceOf13Hits()))
                .priceOf14Hits(priceEntityMapper.map(entity.getPriceOf14Hits()))
                .build();
    }

    @Override
    public Round reverse(RoundDto entity) {
        return Round.builder()
                .date(entity.getDate())
                .week(entity.getWeek())
                .year(entity.getYear())
                .round(entity.getRound())
                .outcomes(outcomeSetEntityMapper.reverse(entity.getOutcomeSetDto()))
                .numOfGames10Hits(entity.getNumOfGames10Hits())
                .numOfGames11Hits(entity.getNumOfGames11Hits())
                .numOfGames12Hits(entity.getNumOfGames12Hits())
                .numOfGames13Hits(entity.getNumOfGames13Hits())
                .numOfGames14Hits(entity.getNumOfGames14Hits())
                .priceOf10Hits(priceEntityMapper.reverse(entity.getPriceOf10Hits()))
                .priceOf11Hits(priceEntityMapper.reverse(entity.getPriceOf11Hits()))
                .priceOf12Hits(priceEntityMapper.reverse(entity.getPriceOf12Hits()))
                .priceOf13Hits(priceEntityMapper.reverse(entity.getPriceOf13Hits()))
                .priceOf14Hits(priceEntityMapper.reverse(entity.getPriceOf14Hits()))
                .build();
    }
}
