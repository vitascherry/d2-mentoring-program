package com.example.training.sportsbetting.guice;

import com.example.training.common.guice.CsvMapperModule;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.google.inject.name.Names;

public class SportsBettingMockCsvMapperModule extends CsvMapperModule {

    @Override
    protected void configure() {
        CsvMapper csvMapper = createCsvMapper();
        bind(CsvMapper.class).annotatedWith(Names.named("sportsBettingMockCsvMapper")).toInstance(csvMapper);
    }
}
