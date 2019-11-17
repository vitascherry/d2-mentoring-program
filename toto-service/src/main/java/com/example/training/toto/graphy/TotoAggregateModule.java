package com.example.training.toto.graphy;

import com.example.training.common.mapper.EntityMapper;
import com.example.training.common.provider.EntityProvider;
import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.factory.SingletonFactory;
import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;
import com.example.training.toto.domain.*;
import com.example.training.toto.dto.*;
import com.example.training.toto.repository.TotoRepository;
import com.example.training.toto.service.TotoService;
import com.example.training.toto.service.impl.TotoServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;

import java.time.LocalDate;
import java.util.List;

import static com.example.training.toto.graphy.TotoEntityMapperModule.*;

public class TotoAggregateModule implements Module {

    static final TypeReference<EntityProvider<LocalDate, Round>> ENTITY_PROVIDER_TYPE_REFERENCE = new TypeReference<EntityProvider<LocalDate, Round>>() {};

    @Override
    public void configure(Linker linker) {
        new TotoEntityMapperModule().configure(linker);

        linker.install(TotoService.class, SingletonFactory.of(this::createTotoService));
        new TotoModule().configure(linker);
    }

    protected TotoService createTotoService(Linker linker) {
        Factory<TotoRepository> totoRepositoryFactory = linker.factoryFor(TotoRepository.class);
        Factory<EntityMapper<OutcomeSet, List<Outcome>>> outcomesMapperFactory = linker.factoryFor(OUTCOMES_MAPPER_TYPE_REFERENCE.getType());
        Factory<EntityMapper<OutcomeSetDto, List<OutcomeDto>>> outcomeDtosMapperFactory = linker.factoryFor(OUTCOME_DTOS_MAPPER_TYPE_REFERENCE.getType());
        Factory<EntityMapper<Price, PriceDto>> priceDtoMapperFactory = linker.factoryFor(PRICE_MAPPER_TYPE_REFERENCE.getType());
        Factory<EntityMapper<Round, RoundDto>> roundDtoMapperFactory = linker.factoryFor(ROUND_MAPPER_TYPE_REFERENCE.getType());
        Factory<EntityMapper<Distribution, DistributionDto>> distributionDtoMapperFactory = linker.factoryFor(DISTRIBUTION_MAPPER_TYPE_REFERENCE.getType());

        TotoRepository totoRepository = totoRepositoryFactory.get(linker);
        EntityMapper<OutcomeSet, List<Outcome>> outcomesMapper = outcomesMapperFactory.get(linker);
        EntityMapper<OutcomeSetDto, List<OutcomeDto>> outcomeDtosMapper = outcomeDtosMapperFactory.get(linker);
        EntityMapper<Price, PriceDto> priceDtoMapper = priceDtoMapperFactory.get(linker);
        EntityMapper<Round, RoundDto> roundDtoMapper = roundDtoMapperFactory.get(linker);
        EntityMapper<Distribution, DistributionDto> distributionDtoMapper = distributionDtoMapperFactory.get(linker);
        return TotoServiceImpl.builder()
                .distributionDtoMapper(distributionDtoMapper)
                .outcomeDtosMapper(outcomeDtosMapper)
                .outcomesMapper(outcomesMapper)
                .priceDtoMapper(priceDtoMapper)
                .roundDtoMapper(roundDtoMapper)
                .totoRepository(totoRepository)
                .build();
    }
}
