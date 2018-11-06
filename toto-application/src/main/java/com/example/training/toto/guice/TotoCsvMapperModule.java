package com.example.training.toto.guice;

import com.example.training.common.guice.CsvMapperModule;
import com.example.training.common.guice.DecimalModule;
import com.example.training.toto.domain.Outcome;
import com.example.training.toto.domain.Price;
import com.example.training.toto.mapper.OutcomeDeserializer;
import com.example.training.toto.mapper.PriceDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

@AllArgsConstructor
public class TotoCsvMapperModule extends CsvMapperModule {

    private final DecimalModule decimalModule;

    @Override
    protected void bindCsvMapper() {
        csvMapper = (CsvMapper) new CsvMapper()
                .registerModule(new JavaTimeModule())
                .registerModule(new SimpleModule()
                        .addDeserializer(Price.class, new PriceDeserializer(decimalModule.getDecimalFormat()))
                        .addDeserializer(Outcome.class, new OutcomeDeserializer()))
                .disable(FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(WRITE_DATES_AS_TIMESTAMPS);

        bind(CsvMapper.class).toInstance(csvMapper);
    }
}
