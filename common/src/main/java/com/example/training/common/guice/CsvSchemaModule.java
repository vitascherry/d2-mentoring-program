package com.example.training.common.guice;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.inject.AbstractModule;

import static com.fasterxml.jackson.dataformat.csv.CsvSchema.emptySchema;

public class CsvSchemaModule extends AbstractModule {

    @Override
    protected void configure() {
        CsvSchema csvSchema = createCsvSchema();
        bind(CsvSchema.class).toInstance(csvSchema);
    }

    protected CsvSchema createCsvSchema() {
        return emptySchema();
    }
}
