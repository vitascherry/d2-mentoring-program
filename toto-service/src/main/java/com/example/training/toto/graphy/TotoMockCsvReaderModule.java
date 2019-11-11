package com.example.training.toto.graphy;

import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.factory.SingletonFactory;
import com.example.training.graphy.key.Key;
import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;
import com.example.training.common.reader.CsvReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class TotoMockCsvReaderModule implements Module {

    static final Key<CsvReader> CSV_READER_KEY = Key.<CsvReader>builder()
            .withClass(CsvReader.class)
            .withName("totoMockCsvReader")
            .build();

    @Override
    public void configure(Linker linker) {
        new TotoMockCsvMapperModule().configure(linker);
        new TotoMockCsvSchemaModule().configure(linker);

        linker.install(CSV_READER_KEY, SingletonFactory.of(this::createCsvReader));
    }

    protected CsvReader createCsvReader(Linker linker) {
        Factory<CsvSchema> csvSchemaFactory = linker.factoryFor(TotoMockCsvSchemaModule.CSV_SCHEMA_KEY);
        Factory<CsvMapper> csvMapperFactory = linker.factoryFor(TotoMockCsvMapperModule.CSV_MAPPER_KEY);

        CsvSchema csvSchema = csvSchemaFactory.get(linker);
        CsvMapper csvMapper = csvMapperFactory.get(linker);

        return new CsvReader(csvMapper, csvSchema);
    }
}
