package com.example.training.sportsbetting.guice;

import com.example.training.common.guice.CsvReaderModule;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class SportsBettingCsvReaderModule extends CsvReaderModule {

    @Override
    protected void bindCsvSchema() {
        csvSchema = CsvSchema.builder()
                .setColumnSeparator(',')
                .setLineSeparator(System.getProperty("line.separator"))
                .setNullValue("-")
                .setUseHeader(true)
                .setSkipFirstDataRow(false)
                .setStrictHeaders(true)
                .build();

        bind(CsvSchema.class).toInstance(csvSchema);
    }
}
