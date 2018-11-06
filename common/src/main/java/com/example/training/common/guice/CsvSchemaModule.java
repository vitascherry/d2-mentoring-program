package com.example.training.common.guice;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.inject.AbstractModule;

import static com.fasterxml.jackson.dataformat.csv.CsvSchema.emptySchema;

public class CsvSchemaModule extends AbstractModule {

    protected CsvSchema csvSchema;

    @Override
    protected void configure() {
        bindCsvSchema();
    }

    protected void bindCsvSchema() {
        csvSchema = emptySchema();

        bind(CsvSchema.class).toInstance(csvSchema);
    }
}
