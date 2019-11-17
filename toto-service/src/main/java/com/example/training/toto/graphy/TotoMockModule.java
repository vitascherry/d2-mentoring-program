package com.example.training.toto.graphy;

import com.example.training.common.mapper.EntityMapper;
import com.example.training.common.provider.EntityProvider;
import com.example.training.common.reader.CsvReader;
import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.factory.SingletonFactory;
import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;
import com.example.training.toto.domain.Round;
import com.example.training.toto.dto.RoundDto;
import com.example.training.toto.provider.RoundMockEntityProvider;
import com.example.training.toto.repository.TotoRepository;
import com.example.training.toto.repository.impl.TotoMockRepository;

import java.time.LocalDate;

import static com.example.training.toto.graphy.TotoAggregateModule.ENTITY_PROVIDER_TYPE_REFERENCE;
import static com.example.training.toto.graphy.TotoAggregateModule.ROUND_MAPPER_TYPE_REFERENCE;

public class TotoMockModule implements Module {

    @Override
    public void configure(Linker linker) {
        new TotoMockCsvReaderModule().configure(linker);

        linker.install(ENTITY_PROVIDER_TYPE_REFERENCE.getType(), SingletonFactory.of(this::createRoundEntityProvider));
        linker.install(TotoRepository.class, SingletonFactory.of(this::createTotoRepository));
    }

    protected EntityProvider<LocalDate, Round> createRoundEntityProvider(Linker linker) {
        Factory<CsvReader> csvReaderFactory = linker.factoryFor(TotoMockCsvReaderModule.CSV_READER_KEY);
        Factory<EntityMapper<Round, RoundDto>> roundMapperFactory = linker.factoryFor(ROUND_MAPPER_TYPE_REFERENCE.getType());

        CsvReader csvReader = csvReaderFactory.get(linker);
        EntityMapper<Round, RoundDto> roundMapper = roundMapperFactory.get(linker);
        return new RoundMockEntityProvider(csvReader, roundMapper);
    }

    protected TotoRepository createTotoRepository(Linker linker) {
        Factory<EntityProvider<LocalDate, Round>> entityProviderFactory = linker.factoryFor(ENTITY_PROVIDER_TYPE_REFERENCE.getType());
        EntityProvider<LocalDate, Round> provider = entityProviderFactory.get(linker);
        return new TotoMockRepository(provider);
    }
}
