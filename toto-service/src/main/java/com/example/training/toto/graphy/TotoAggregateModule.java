package com.example.training.toto.graphy;

import com.example.training.common.mapper.EntityMapper;
import com.example.training.common.provider.EntityProvider;
import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.factory.SingletonFactory;
import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;
import com.example.training.toto.domain.*;
import com.example.training.toto.dto.*;
import com.example.training.toto.mapper.*;
import com.example.training.toto.repository.TotoRepository;
import com.example.training.toto.repository.impl.TotoRepositoryImpl;
import com.example.training.toto.service.TotoService;
import com.example.training.toto.service.impl.TotoServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;

import java.time.LocalDate;
import java.util.List;

public class TotoAggregateModule implements Module {

    static final TypeReference<EntityProvider<LocalDate, Round>> ENTITY_PROVIDER_TYPE_REFERENCE = new TypeReference<EntityProvider<LocalDate, Round>>() {};
    static final TypeReference<EntityMapper<OutcomeSet, List<Outcome>>> OUTCOMES_MAPPER_TYPE_REFERENCE = new TypeReference<EntityMapper<OutcomeSet, List<Outcome>>>() {};
    static final TypeReference<EntityMapper<OutcomeSetDto, List<OutcomeDto>>> OUTCOME_DTOS_MAPPER_TYPE_REFERENCE = new TypeReference<EntityMapper<OutcomeSetDto, List<OutcomeDto>>>() {};
    static final TypeReference<EntityMapper<Price, PriceDto>> PRICE_MAPPER_TYPE_REFERENCE = new TypeReference<EntityMapper<Price, PriceDto>>() {};
    static final TypeReference<EntityMapper<Round, RoundDto>> ROUND_MAPPER_TYPE_REFERENCE = new TypeReference<EntityMapper<Round, RoundDto>>() {};
    static final TypeReference<EntityMapper<Distribution, DistributionDto>> DISTRIBUTION_MAPPER_TYPE_REFERENCE = new TypeReference<EntityMapper<Distribution, DistributionDto>>() {};
    static final TypeReference<EntityMapper<OutcomeSet, OutcomeSetDto>> OUTCOME_SET_MAPPER_TYPE_REFERENCE = new TypeReference<EntityMapper<OutcomeSet, OutcomeSetDto>>() {};
    static final TypeReference<EntityMapper<Outcome, OutcomeDto>> OUTCOME_MAPPER_TYPE_REFERENCE = new TypeReference<EntityMapper<Outcome, OutcomeDto>>() {};

    @Override
    public void configure(Linker linker) {
        linker.install(TotoRepository.class, SingletonFactory.of(this::createTotoRepository));
        linker.install(OUTCOMES_MAPPER_TYPE_REFERENCE.getType(), SingletonFactory.of(this::createOutcomesEntityMapper));
        linker.install(OUTCOME_DTOS_MAPPER_TYPE_REFERENCE.getType(), SingletonFactory.of(this::createOutcomeDtosEntityMapper));
        linker.install(PRICE_MAPPER_TYPE_REFERENCE.getType(), SingletonFactory.of(this::createPriceEntityMapper));
        linker.install(ROUND_MAPPER_TYPE_REFERENCE.getType(), SingletonFactory.of(this::createRoundEntityMapper));
        linker.install(DISTRIBUTION_MAPPER_TYPE_REFERENCE.getType(), SingletonFactory.of(this::createDistributionEntityMapper));
        linker.install(OUTCOME_SET_MAPPER_TYPE_REFERENCE.getType(), SingletonFactory.of(this::createOutcomeSetEntityMapper));
        linker.install(OUTCOME_MAPPER_TYPE_REFERENCE.getType(), SingletonFactory.of(this::createOutcomeEntityMapper));
        linker.install(TotoService.class, SingletonFactory.of(this::createTotoService));

        // This allows to migrate from mock data to real data gradually, without changing the aggregation module
        linker.merge(new TotoMockModule(), new TotoModule());
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

    protected TotoRepository createTotoRepository(Linker linker) {
        Factory<EntityProvider<LocalDate, Round>> entityProviderFactory = linker.factoryFor(ENTITY_PROVIDER_TYPE_REFERENCE.getType());
        EntityProvider<LocalDate, Round> provider = entityProviderFactory.get(linker);
        return new TotoRepositoryImpl(provider);
    }

    protected EntityMapper<OutcomeSet, List<Outcome>> createOutcomesEntityMapper(Linker linker) {
        return new OutcomesEntityMapper();
    }

    protected EntityMapper<OutcomeSetDto, List<OutcomeDto>> createOutcomeDtosEntityMapper(Linker linker) {
        return new OutcomeDtosEntityMapper();
    }

    protected EntityMapper<Price, PriceDto> createPriceEntityMapper(Linker linker) {
        return new PriceEntityMapper();
    }

    protected EntityMapper<Round, RoundDto> createRoundEntityMapper(Linker linker) {
        Factory<EntityMapper<OutcomeSet, OutcomeSetDto>> outcomeSetEntityMapperFactory = linker.factoryFor(OUTCOME_SET_MAPPER_TYPE_REFERENCE.getType());
        Factory<EntityMapper<Price, PriceDto>> priceEntityMapperFactory = linker.factoryFor(PRICE_MAPPER_TYPE_REFERENCE.getType());

        EntityMapper<OutcomeSet, OutcomeSetDto> outcomeSetEntityMapper = outcomeSetEntityMapperFactory.get(linker);
        EntityMapper<Price, PriceDto> priceEntityMapper = priceEntityMapperFactory.get(linker);
        return new RoundEntityMapper(outcomeSetEntityMapper, priceEntityMapper);
    }

    protected EntityMapper<Distribution, DistributionDto> createDistributionEntityMapper(Linker linker) {
        return new DistributionEntityMapper();
    }

    protected EntityMapper<OutcomeSet, OutcomeSetDto> createOutcomeSetEntityMapper(Linker linker) {
        Factory<EntityMapper<Outcome, OutcomeDto>> factory = linker.factoryFor(OUTCOME_MAPPER_TYPE_REFERENCE.getType());
        EntityMapper<Outcome, OutcomeDto> outcomeEntityMapper = factory.get(linker);
        return new OutcomeSetEntityMapper(outcomeEntityMapper);
    }

    protected EntityMapper<Outcome, OutcomeDto> createOutcomeEntityMapper(Linker linker) {
        return new OutcomeEntityMapper();
    }
}
