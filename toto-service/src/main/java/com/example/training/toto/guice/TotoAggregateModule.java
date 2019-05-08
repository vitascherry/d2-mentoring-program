package com.example.training.toto.guice;

import com.example.training.toto.repository.TotoRepository;
import com.example.training.toto.service.TotoService;
import com.example.training.toto.service.impl.TotoServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.util.Modules;

public class TotoAggregateModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TotoService.class).to(TotoServiceImpl.class);

        install(new TotoDecimalModule());

        // This allows to migrate from mock data to real data gradually, without changing the aggregation module
        install(Modules.override(new TotoMockModule()).with(new TotoModule()));
    }

    @Singleton
    @Provides
    public TotoServiceImpl totoServiceImplProvider(TotoRepository totoRepository) {
        return new TotoServiceImpl(totoRepository);
    }
}
