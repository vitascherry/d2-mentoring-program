package com.example.training.toto.guice;

import com.example.training.common.reader.CsvReader;
import com.example.training.toto.provider.RoundMockEntityProvider;
import com.example.training.toto.repository.TotoRepository;
import com.example.training.toto.repository.impl.MockTotoRepository;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

public class TotoMockModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TotoRepository.class).to(MockTotoRepository.class);

        install(new TotoMockCsvSchemaModule());
        install(new TotoMockCsvMapperModule());
        install(new TotoMockCsvReaderModule());
    }

    @Singleton
    @Provides
    public MockTotoRepository mockTotoRepositoryProvider(RoundMockEntityProvider mockProvider) {
        return new MockTotoRepository(mockProvider);
    }

    @Singleton
    @Provides
    public RoundMockEntityProvider roundMockProvider(@Named("totoMockCsvReader") CsvReader csvReader) {
        return new RoundMockEntityProvider(csvReader);
    }
}
