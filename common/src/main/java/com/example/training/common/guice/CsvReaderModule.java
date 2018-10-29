package com.example.training.common.guice;

import com.example.training.common.reader.CsvReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.AbstractModule;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static com.fasterxml.jackson.dataformat.csv.CsvSchema.emptySchema;

public class CsvReaderModule extends AbstractModule {

    protected CsvMapper csvMapper;
    protected CsvSchema csvSchema;

    @Override
    protected void configure() {
        bindCsvMapper();
        bindCsvSchema();
        bindCsvReader();
    }

    protected void bindCsvReader() {
        bind(CsvReader.class).toInstance(new CsvReader(csvMapper, csvSchema));
    }

    protected void bindCsvMapper() {
        csvMapper = (CsvMapper) new CsvMapper()
                .registerModule(new JavaTimeModule())
                .disable(FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(WRITE_DATES_AS_TIMESTAMPS);

        bind(CsvMapper.class).toInstance(csvMapper);
    }

    protected void bindCsvSchema() {
        csvSchema = emptySchema();

        bind(CsvSchema.class).toInstance(csvSchema);
    }
}
