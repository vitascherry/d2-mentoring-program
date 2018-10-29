package com.example.training.sportsbetting.guice;

import com.example.training.common.guice.ObjectMapperModule;
import com.example.training.common.reader.CsvReader;
import com.example.training.sportsbetting.provider.MockBetProvider;
import com.example.training.sportsbetting.provider.MockOutcomeOddProvider;
import com.example.training.sportsbetting.provider.MockOutcomeProvider;
import com.example.training.sportsbetting.provider.MockSportEventProvider;
import com.example.training.sportsbetting.repository.SportEventRepository;
import com.example.training.sportsbetting.repository.impl.MockSportEventRepository;
import com.example.training.sportsbetting.service.SportEventService;
import com.example.training.sportsbetting.service.impl.MockSportEventService;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class SportsBettingModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new SportsBettingCsvReaderModule());
        install(new ObjectMapperModule());

        bind(SportEventService.class).to(MockSportEventService.class);
        bind(SportEventRepository.class).to(MockSportEventRepository.class);
    }

    @Singleton
    @Provides
    public MockSportEventService mockSportEventServiceProvider(MockSportEventRepository repository) {
        return new MockSportEventService(repository);
    }

    @Singleton
    @Provides
    public MockSportEventRepository mockSportEventRepositoryProvider(MockSportEventProvider provider) {
        return new MockSportEventRepository(provider);
    }

    @Singleton
    @Provides
    public MockSportEventProvider mockSportEventProvider(MockBetProvider betProvider, CsvReader reader) {
        return new MockSportEventProvider(betProvider, reader);
    }

    @Singleton
    @Provides
    public MockBetProvider mockBetProvider(MockOutcomeProvider provider, CsvReader reader) {
        return new MockBetProvider(provider, reader);
    }

    @Singleton
    @Provides
    public MockOutcomeProvider outcomeProvider(MockOutcomeOddProvider provider, CsvReader reader) {
        return new MockOutcomeProvider(provider, reader);
    }

    @Singleton
    @Provides
    public MockOutcomeOddProvider mockOutcomeOddProvider(CsvReader reader) {
        return new MockOutcomeOddProvider(reader);
    }
}
