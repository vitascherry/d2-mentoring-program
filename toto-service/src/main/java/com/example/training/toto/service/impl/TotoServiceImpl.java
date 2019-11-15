package com.example.training.toto.service.impl;

import com.example.training.common.mapper.EntityMapper;
import com.example.training.toto.domain.*;
import com.example.training.toto.dto.*;
import com.example.training.toto.exception.RoundNotFoundException;
import com.example.training.toto.repository.TotoRepository;
import com.example.training.toto.service.TotoService;
import lombok.Builder;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.training.toto.service.impl.DistributionCollector.toDistributions;
import static com.example.training.toto.util.CurrencyUtils.extractCurrencyOrDefault;
import static java.math.BigDecimal.ZERO;
import static java.util.Comparator.comparing;

@Builder
public class TotoServiceImpl implements TotoService {

    private final TotoRepository totoRepository;
    private final EntityMapper<OutcomeSet, List<Outcome>> outcomesMapper;
    private final EntityMapper<OutcomeSetDto, List<OutcomeDto>> outcomeDtosMapper;
    private final EntityMapper<Price, PriceDto> priceDtoMapper;
    private final EntityMapper<Round, RoundDto> roundDtoMapper;
    private final EntityMapper<Distribution, DistributionDto> distributionDtoMapper;

    @Override
    public PriceDto getLargestPrice() {
        final List<Round> rounds = totoRepository.getAllRounds();
        final Currency currency = rounds.stream()
                .map(round -> extractCurrencyOrDefault(round, null))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
        final PriceDto zeroPrice = new PriceDto(ZERO, currency);

        return Stream.of(
                rounds.stream()
                        .map(Round::getPriceOf10Hits)
                        .max(comparing(Price::getAmount, BigDecimal::compareTo)),
                rounds.stream()
                        .map(Round::getPriceOf11Hits)
                        .max(comparing(Price::getAmount, BigDecimal::compareTo)),
                rounds.stream()
                        .map(Round::getPriceOf12Hits)
                        .max(comparing(Price::getAmount, BigDecimal::compareTo)),
                rounds.stream()
                        .map(Round::getPriceOf13Hits)
                        .max(comparing(Price::getAmount, BigDecimal::compareTo)),
                rounds.stream()
                        .map(Round::getPriceOf14Hits)
                        .max(comparing(Price::getAmount, BigDecimal::compareTo)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(priceDtoMapper::map)
                .max(comparing(PriceDto::getAmount, BigDecimal::compareTo))
                .orElse(zeroPrice);
    }

    @Override
    public List<DistributionDto> getDistributions() {
        return totoRepository.getAllRounds()
                .stream()
                .map(Round::getOutcomes)
                .map(outcomesMapper::map)
                .collect(Collectors.collectingAndThen(toDistributions(), distributionDtoMapper::mapAll));
    }

    @Override
    public RoundDto getRound(final LocalDate date) throws RoundNotFoundException {
        return totoRepository.getRoundByDate(date)
                .map(roundDtoMapper::map)
                .orElseThrow(() -> new RoundNotFoundException(date));
    }

    @Override
    public boolean hasRound(LocalDate date) {
        return totoRepository.getRoundByDate(date).isPresent();
    }

    @Override
    public BetResultDto calculateWager(@NonNull WagerDto wager) {
        final RoundDto roundDto = wager.getRoundDto();
        final PriceDto zeroPriceDto = new PriceDto(ZERO, extractCurrencyOrDefault(wager.getRoundDto(), null));
        final List<OutcomeDto> realOutcomes = outcomeDtosMapper.map(roundDto.getOutcomeSetDto());
        final List<OutcomeDto> wagerOutcomes = outcomeDtosMapper.map(wager.getOutcomeSetDto());
        final List<HitDto> hits = new ArrayList<>();
        for (int i = 0; i < 14 && realOutcomes.get(i) == wagerOutcomes.get(i); i++) {
            hits.add(HitDto.builder()
                    .date(roundDto.getDate())
                    .game(i + 1)
                    .priceDto(i == 9 ? roundDto.getPriceOf10Hits() :
                            i == 10 ? roundDto.getPriceOf11Hits() :
                                    i == 11 ? roundDto.getPriceOf12Hits() :
                                            i == 12 ? roundDto.getPriceOf13Hits() :
                                                    i == 13 ? roundDto.getPriceOf14Hits() : zeroPriceDto)
                    .round(roundDto.getRound())
                    .outcomeDto(realOutcomes.get(i))
                    .build());
        }

        PriceDto price = hits.stream()
                .map(HitDto::getPriceDto)
                .max(comparing(PriceDto::getAmount, BigDecimal::compareTo))
                .orElse(zeroPriceDto);

        return new BetResultDto(hits, price);
    }
}
