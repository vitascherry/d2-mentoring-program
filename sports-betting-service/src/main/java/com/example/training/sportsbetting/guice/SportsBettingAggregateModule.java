package com.example.training.sportsbetting.guice;

import com.example.training.common.provider.EntityProvider;
import com.example.training.sportsbetting.domain.Outcome;
import com.example.training.sportsbetting.domain.SportEvent;
import com.example.training.sportsbetting.domain.identifier.DomainEntityIdentifier;
import com.example.training.sportsbetting.repository.SportsBettingRepository;
import com.example.training.sportsbetting.repository.impl.SportsBettingRepositoryImpl;
import com.example.training.sportsbetting.service.SportsBettingService;
import com.example.training.sportsbetting.service.impl.SportsBettingServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.util.Modules;

import java.util.List;

public class SportsBettingAggregateModule extends AbstractModule {

    @Override
    protected void configure() {
        // This allows to migrate from mock data to real data gradually, without changing the aggregation module
        install(Modules.override(new SportsBettingMockModule()).with(new SportsBettingModule()));
    }

    @Singleton
    @Provides
    public SportsBettingService sportEventServiceImplProvider(SportsBettingRepository repository) {
        return new SportsBettingServiceImpl(repository);
    }

    @Singleton
    @Provides
    public SportsBettingRepository sportEventRepositoryImplProvider(
            EntityProvider<DomainEntityIdentifier, SportEvent> sportEventProvider,
            EntityProvider<DomainEntityIdentifier, List<Outcome>> resultsProvider) {
        return new SportsBettingRepositoryImpl(sportEventProvider, resultsProvider);
    }
}
