package com.example.training.toto.graphy;

import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.key.Key;
import com.example.training.graphy.linker.Linker;
import com.example.training.common.graphy.CsvMapperModule;
import com.example.training.toto.domain.Outcome;
import com.example.training.toto.domain.Price;
import com.example.training.toto.mapper.OutcomeDeserializer;
import com.example.training.toto.mapper.PriceDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

import java.text.DecimalFormat;

public class TotoMockCsvMapperModule extends CsvMapperModule {

    public static final Key<CsvMapper> CSV_MAPPER_KEY = Key.<CsvMapper>builder()
            .withClass(CsvMapper.class)
            .withName("totoMockCsvMapper")
            .build();

    @Override
    public void configure(Linker linker) {
        new TotoMockDecimalFormatModule().configure(linker);

        linker.install(CSV_MAPPER_KEY, this::createCsvMapper);
    }

    @Override
    protected CsvMapper createCsvMapper(Linker linker) {
        Factory<DecimalFormat> decimalFormatFactory = linker.factoryFor(TotoMockDecimalFormatModule.DECIMAL_FORMAT_KEY);
        DecimalFormat decimalFormat = decimalFormatFactory.get(linker);

        return (CsvMapper) super.createCsvMapper(linker)
                .registerModule(new SimpleModule()
                        .addDeserializer(Price.class, new PriceDeserializer(decimalFormat))
                        .addDeserializer(Outcome.class, new OutcomeDeserializer()));
    }
}
