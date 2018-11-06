package com.example.training.sportsbetting.guice;

import com.example.training.common.guice.*;
import com.example.training.common.handler.Printer;
import com.example.training.common.handler.Reader;
import com.example.training.common.reader.CsvReader;
import com.example.training.sportsbetting.handler.SportsBettingConsoleHandler;
import com.example.training.sportsbetting.provider.*;
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
        install(new DecimalModule());
        install(new PrinterModule());
        install(new ReaderModule());

        CsvSchemaModule csvSchemaModule = new SportsBettingCsvSchemaModule();
        install(csvSchemaModule);

        CsvMapperModule csvMapperModule = new CsvMapperModule();
        install(csvMapperModule);

        install(new CsvReaderModule(csvSchemaModule, csvMapperModule));

        bind(SportsBettingService.class).to(MockSportsBettingService.class);
        bind(SportsBettingRepository.class).to(MockSportsBettingRepository.class);
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
