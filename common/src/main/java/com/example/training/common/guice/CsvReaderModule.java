package com.example.training.common.guice;

import com.example.training.common.reader.CsvReader;
import com.google.inject.AbstractModule;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CsvReaderModule extends AbstractModule {

    private final CsvSchemaModule csvSchemaModule;
    private final CsvMapperModule csvMapperModule;

    @Override
    protected void configure() {
        bindCsvReader();
    }

    protected void bindCsvReader() {
        bind(CsvReader.class).toInstance(new CsvReader(csvMapperModule.csvMapper, csvSchemaModule.csvSchema));
    }
}
