package com.example.training.sportsbetting.guice;

import com.example.training.common.guice.CsvSchemaModule;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.inject.name.Names;

public class SportsBettingMockCsvSchemaModule extends CsvSchemaModule {

    @Override
    protected void configure() {
        CsvSchema csvSchema = createCsvSchema();
        bind(CsvSchema.class).annotatedWith(Names.named("sportsBettingMockCsvSchema")).toInstance(csvSchema);
    }

    @Override
    protected CsvSchema createCsvSchema() {
        return CsvSchema.builder()
                .setColumnSeparator(',')
                .setLineSeparator(System.getProperty("line.separator"))
                .setNullValue("-")
                .setUseHeader(true)
                .setSkipFirstDataRow(false)
                .setStrictHeaders(true)
                .build();
    }
}
