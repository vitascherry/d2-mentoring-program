package com.example.training.sportsbetting.guice;

import com.example.training.common.guice.CsvMapperModule;
import com.example.training.common.guice.CsvReaderModule;
import com.example.training.common.guice.DecimalModule;
import com.example.training.common.guice.PrinterModule;
import com.example.training.common.guice.ReaderModule;
import com.example.training.common.handler.Printer;
import com.example.training.common.handler.Reader;
import com.example.training.common.reader.CsvReader;
import com.example.training.sportsbetting.handler.SportsBettingConsoleHandler;
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

import java.text.DecimalFormat;

public class SportsBettingModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SportsBettingService.class).to(MockSportsBettingService.class);
        bind(SportsBettingRepository.class).to(MockSportsBettingRepository.class);

        install(new DecimalModule());
        install(new PrinterModule());
        install(new ReaderModule());
        install(new SportsBettingCsvSchemaModule());
        install(new CsvMapperModule());
        install(new CsvReaderModule());
    }

    @Singleton
    @Provides
    public SportsBettingConsoleHandler sportsBettingConsoleHandlerProvider(Printer printer, Reader reader,
                                                                           SportsBettingService sportsBettingService,
                                                                           DecimalFormat decimalFormatter) {
        return SportsBettingConsoleHandler.builder()
                .printer(printer)
                .reader(reader)
                .sportsBettingService(sportsBettingService)
                .decimalFormatter(decimalFormatter)
                .build();
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
    public MockSportEventResultsProvider mockSportEventResultsProvider(CsvReader csvReader) {
        return new MockSportEventResultsProvider(csvReader);
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
