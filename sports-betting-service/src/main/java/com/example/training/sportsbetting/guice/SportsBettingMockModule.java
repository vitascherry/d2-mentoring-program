package com.example.training.sportsbetting.guice;

import com.example.training.common.reader.CsvReader;
import com.example.training.sportsbetting.provider.MockBetProvider;
import com.example.training.sportsbetting.provider.MockOutcomeOddProvider;
import com.example.training.sportsbetting.provider.MockOutcomeProvider;
import com.example.training.sportsbetting.provider.MockSportEventProvider;
import com.example.training.sportsbetting.provider.MockSportEventResultsProvider;
import com.example.training.sportsbetting.repository.SportsBettingRepository;
import com.example.training.sportsbetting.repository.impl.MockSportsBettingRepository;
import com.example.training.sportsbetting.service.SportsBettingService;
import com.example.training.sportsbetting.service.impl.MockSportsBettingService;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

public class SportsBettingMockModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SportsBettingService.class).to(MockSportsBettingService.class);
        bind(SportsBettingRepository.class).to(MockSportsBettingRepository.class);

        install(new SportsBettingMockCsvSchemaModule());
        install(new SportsBettingMockCsvMapperModule());
        install(new SportsBettingMockCsvReaderModule());
    }

    @Singleton
    @Provides
    public MockSportsBettingService mockSportEventServiceProvider(MockSportsBettingRepository repository) {
        return new MockSportsBettingService(repository);
    }

    @Singleton
    @Provides
    public MockSportsBettingRepository mockSportEventRepositoryProvider(MockSportEventProvider sportEventProvider,
                                                                        MockSportEventResultsProvider resultsProvider) {
        return new MockSportsBettingRepository(sportEventProvider, resultsProvider);
    }

    @Singleton
    @Provides
    public MockSportEventResultsProvider mockSportEventResultsProvider(@Named("sportsBettingMockCsvReader") CsvReader csvReader) {
        return new MockSportEventResultsProvider(csvReader);
    }

    @Singleton
    @Provides
    public MockSportEventProvider mockSportEventProvider(MockBetProvider betProvider,
                                                         @Named("sportsBettingMockCsvReader") CsvReader reader) {
        return new MockSportEventProvider(betProvider, reader);
    }

    @Singleton
    @Provides
    public MockBetProvider mockBetProvider(MockOutcomeProvider provider,
                                           @Named("sportsBettingMockCsvReader") CsvReader reader) {
        return new MockBetProvider(provider, reader);
    }

    @Singleton
    @Provides
    public MockOutcomeProvider mockOutcomeProvider(MockOutcomeOddProvider provider,
                                                   @Named("sportsBettingMockCsvReader") CsvReader reader) {
        return new MockOutcomeProvider(provider, reader);
    }

    @Singleton
    @Provides
    public MockOutcomeOddProvider mockOutcomeOddProvider(@Named("sportsBettingMockCsvReader") CsvReader reader) {
        return new MockOutcomeOddProvider(reader);
    }
}
