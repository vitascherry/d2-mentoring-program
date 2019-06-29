package com.example.training.toto.guice;

import com.example.training.common.provider.EntityProvider;
import com.example.training.common.reader.CsvReader;
import com.example.training.toto.domain.Round;
import com.example.training.toto.provider.RoundMockEntityProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import java.time.LocalDate;

public class TotoMockModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new TotoMockCsvSchemaModule());
        install(new TotoMockCsvMapperModule());
        install(new TotoMockCsvReaderModule());
    }

    @Singleton
    @Provides
    public EntityProvider<LocalDate, Round> roundMockProvider(@Named("totoMockCsvReader") CsvReader csvReader) {
        return new RoundMockEntityProvider(csvReader);
    }
}
