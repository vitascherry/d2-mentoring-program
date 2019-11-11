package com.example.training.toto.graphy;

import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.factory.SingletonFactory;
import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;
import com.example.training.common.provider.EntityProvider;
import com.example.training.toto.domain.Round;
import com.example.training.toto.repository.TotoRepository;
import com.example.training.toto.repository.impl.TotoRepositoryImpl;
import com.example.training.toto.service.TotoService;
import com.example.training.toto.service.impl.TotoServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;

import java.time.LocalDate;

public class TotoAggregateModule implements Module {

    static final TypeReference<EntityProvider<LocalDate, Round>> ENTITY_PROVIDER_TYPE_REFERENCE = new TypeReference<EntityProvider<LocalDate, Round>>() {};

    @Override
    public void configure(Linker linker) {
        linker.install(TotoService.class, SingletonFactory.of(this::createTotoService));
        linker.install(TotoRepository.class, SingletonFactory.of(this::createTotoRepository));

        // This allows to migrate from mock data to real data gradually, without changing the aggregation module
        linker.merge(new TotoMockModule(), new TotoModule());
    }

    protected TotoService createTotoService(Linker linker) {
        Factory<TotoRepository> totoRepositoryFactory = linker.factoryFor(TotoRepository.class);
        TotoRepository totoRepository = totoRepositoryFactory.get(linker);
        return new TotoServiceImpl(totoRepository);
    }

    protected TotoRepository createTotoRepository(Linker linker) {
        Factory<EntityProvider<LocalDate, Round>> entityProviderFactory = linker.factoryFor(ENTITY_PROVIDER_TYPE_REFERENCE);
        EntityProvider<LocalDate, Round> provider = entityProviderFactory.get(linker);
        return new TotoRepositoryImpl(provider);
    }
}
