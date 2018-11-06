package com.example.training.common.guice;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.AbstractModule;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public class CsvMapperModule extends AbstractModule {

    protected CsvMapper csvMapper;

    @Override
    protected void configure() {
        bindCsvMapper();
    }

    protected void bindCsvMapper() {
        csvMapper = (CsvMapper) new CsvMapper()
                .registerModule(new JavaTimeModule())
                .disable(FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(WRITE_DATES_AS_TIMESTAMPS);

        bind(CsvMapper.class).toInstance(csvMapper);
    }
}
