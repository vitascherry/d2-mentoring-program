package com.example.training.common.guice;

import com.example.training.common.reader.CsvReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class CsvReaderModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Singleton
    @Provides
    public CsvReader csvReaderProvider(CsvMapper csvMapper, CsvSchema csvSchema) {
        return new CsvReader(csvMapper, csvSchema);
    }
}
