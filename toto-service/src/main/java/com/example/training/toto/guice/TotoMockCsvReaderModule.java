package com.example.training.toto.guice;

import com.example.training.common.reader.CsvReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

public class TotoMockCsvReaderModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Singleton
    @Provides
    @Named("totoMockCsvReader")
    public CsvReader csvReaderProvider(@Named("totoMockCsvMapper") CsvMapper csvMapper,
                                       @Named("totoMockCsvSchema") CsvSchema csvSchema) {
        return new CsvReader(csvMapper, csvSchema);
    }
}
