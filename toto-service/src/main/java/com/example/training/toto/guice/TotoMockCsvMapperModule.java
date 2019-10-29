package com.example.training.toto.guice;

import com.example.training.common.guice.CsvMapperModule;
import com.example.training.toto.domain.Outcome;
import com.example.training.toto.domain.Price;
import com.example.training.toto.mapper.OutcomeDeserializer;
import com.example.training.toto.mapper.PriceDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.google.inject.name.Names;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import static java.math.RoundingMode.HALF_EVEN;

public class TotoMockCsvMapperModule extends CsvMapperModule {

    @Override
    protected void configure() {
        CsvMapper csvMapper = createCsvMapper();
        bind(CsvMapper.class).annotatedWith(Names.named("totoMockCsvMapper")).toInstance(csvMapper);
    }

    @Override
    protected CsvMapper createCsvMapper() {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormatSymbols.setMonetaryDecimalSeparator('.');
        decimalFormatSymbols.setGroupingSeparator(' ');
        DecimalFormat decimalFormat = new DecimalFormat("###,###.##", decimalFormatSymbols);
        decimalFormat.setRoundingMode(HALF_EVEN);
        decimalFormat.setParseBigDecimal(true);
        return (CsvMapper) super.createCsvMapper()
                .registerModule(new SimpleModule()
                        .addDeserializer(Price.class, new PriceDeserializer(decimalFormat))
                        .addDeserializer(Outcome.class, new OutcomeDeserializer()));
    }
}
