package com.example.training.common.graphy;

import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public class CsvMapperModule implements Module {

    @Override
    public void configure(Linker linker) {
        linker.install(CsvMapper.class, this::createCsvMapper);
    }

    protected CsvMapper createCsvMapper(Linker linker) {
        return (CsvMapper) new CsvMapper()
                .registerModule(new JavaTimeModule())
                .disable(FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(WRITE_DATES_AS_TIMESTAMPS);
    }
}
