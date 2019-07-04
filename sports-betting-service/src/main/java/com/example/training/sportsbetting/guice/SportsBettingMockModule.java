package com.example.training.sportsbetting.guice;

import com.example.training.common.provider.EntityProvider;
import com.example.training.common.reader.CsvReader;
import com.example.training.sportsbetting.domain.Bet;
import com.example.training.sportsbetting.domain.Outcome;
import com.example.training.sportsbetting.domain.OutcomeOdd;
import com.example.training.sportsbetting.domain.SportEvent;
import com.example.training.sportsbetting.domain.identifier.DomainEntityIdentifier;
import com.example.training.sportsbetting.provider.MockBetProvider;
import com.example.training.sportsbetting.provider.MockOutcomeOddProvider;
import com.example.training.sportsbetting.provider.MockOutcomeProvider;
import com.example.training.sportsbetting.provider.MockSportEventProvider;
import com.example.training.sportsbetting.provider.MockSportEventResultsProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import java.util.List;

public class SportsBettingMockModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new SportsBettingMockCsvSchemaModule());
        install(new SportsBettingMockCsvMapperModule());
        install(new SportsBettingMockCsvReaderModule());
    }

    @Singleton
    @Provides
    public EntityProvider<DomainEntityIdentifier, List<Outcome>> mockSportEventResultsProvider(
            @Named("sportsBettingMockCsvReader") CsvReader csvReader) {
        return new MockSportEventResultsProvider(csvReader);
    }

    @Singleton
    @Provides
    public EntityProvider<DomainEntityIdentifier, SportEvent> mockSportEventProvider(
            EntityProvider<DomainEntityIdentifier, Bet> betProvider,
            @Named("sportsBettingMockCsvReader") CsvReader reader) {
        return new MockSportEventProvider(betProvider, reader);
    }

    @Singleton
    @Provides
    public EntityProvider<DomainEntityIdentifier, Bet> mockBetProvider(
            EntityProvider<DomainEntityIdentifier, Outcome> outcomeProvider,
            @Named("sportsBettingMockCsvReader") CsvReader reader) {
        return new MockBetProvider(outcomeProvider, reader);
    }

    @Singleton
    @Provides
    public EntityProvider<DomainEntityIdentifier, Outcome> mockOutcomeProvider(
            EntityProvider<DomainEntityIdentifier, OutcomeOdd> outcomeOddProvider,
            @Named("sportsBettingMockCsvReader") CsvReader reader) {
        return new MockOutcomeProvider(outcomeOddProvider, reader);
    }

    @Singleton
    @Provides
    public EntityProvider<DomainEntityIdentifier, OutcomeOdd> mockOutcomeOddProvider(
            @Named("sportsBettingMockCsvReader") CsvReader reader) {
        return new MockOutcomeOddProvider(reader);
    }
}
