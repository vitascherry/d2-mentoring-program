package com.example.training.sportsbetting.guice;

import com.example.training.common.reader.CsvReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

public class SportsBettingMockCsvReaderModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Singleton
    @Provides
    @Named("sportsBettingMockCsvReader")
    public CsvReader csvReaderProvider(@Named("sportsBettingMockCsvMapper") CsvMapper csvMapper,
                                       @Named("sportsBettingMockCsvSchema") CsvSchema csvSchema) {
        return new CsvReader(csvMapper, csvSchema);
    }
}
