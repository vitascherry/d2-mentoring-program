package com.example.training.toto.guice;

import com.example.training.common.guice.CsvMapperModule;
import com.example.training.toto.domain.Outcome;
import com.example.training.toto.domain.Price;
import com.example.training.toto.mapper.OutcomeDeserializer;
import com.example.training.toto.mapper.PriceDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import java.text.DecimalFormat;

public class TotoMockCsvMapperModule extends CsvMapperModule {

    @Override
    protected void configure() {
        // not calling super.configure() because of using @Provides instead of explicit binding
    }

    @Singleton
    @Provides
    @Named("totoMockCsvMapper")
    public CsvMapper totoCsvMapperProvider(@Named("totoDecimalFormat") DecimalFormat decimalFormat) {
        return (CsvMapper) createCsvMapper()
                .registerModule(new SimpleModule()
                        .addDeserializer(Price.class, new PriceDeserializer(decimalFormat))
                        .addDeserializer(Outcome.class, new OutcomeDeserializer()));
    }
}
