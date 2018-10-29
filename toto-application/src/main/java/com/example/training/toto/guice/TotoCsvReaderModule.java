package com.example.training.toto.guice;

import com.example.training.common.guice.CsvReaderModule;
import com.example.training.common.guice.DecimalModule;
import com.example.training.toto.domain.Outcome;
import com.example.training.toto.domain.Price;
import com.example.training.toto.mapper.OutcomeDeserializer;
import com.example.training.toto.mapper.PriceDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.text.DecimalFormat;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static com.fasterxml.jackson.dataformat.csv.CsvSchema.ColumnType.NUMBER;
import static com.fasterxml.jackson.dataformat.csv.CsvSchema.ColumnType.STRING;

public class TotoCsvReaderModule extends CsvReaderModule {

    private final TotoDecimalModule decimalModule = new TotoDecimalModule();

    @Override
    protected void configure() {
        install(decimalModule);

        bindCsvMapper();
        bindCsvSchema();
        bindCsvReader();
    }

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

    @Override
    protected void bindCsvSchema() {
        csvSchema = CsvSchema
                .builder()
                .addColumn("year", NUMBER)
                .addColumn("week", NUMBER)
                .addColumn("round", NUMBER)
                .addColumn("date", STRING)
                .addColumn("numOfGames14Hits", NUMBER)
                .addColumn("priceOf14Hits", STRING)
                .addColumn("numOfGames13Hits", NUMBER)
                .addColumn("priceOf13Hits", STRING)
                .addColumn("numOfGames12Hits", NUMBER)
                .addColumn("priceOf12Hits", STRING)
                .addColumn("numOfGames11Hits", NUMBER)
                .addColumn("priceOf11Hits", STRING)
                .addColumn("numOfGames10Hits", NUMBER)
                .addColumn("priceOf10Hits", STRING)
                .addColumn("o1", STRING)
                .addColumn("o2", STRING)
                .addColumn("o3", STRING)
                .addColumn("o4", STRING)
                .addColumn("o5", STRING)
                .addColumn("o6", STRING)
                .addColumn("o7", STRING)
                .addColumn("o8", STRING)
                .addColumn("o9", STRING)
                .addColumn("o10", STRING)
                .addColumn("o11", STRING)
                .addColumn("o12", STRING)
                .addColumn("o13", STRING)
                .addColumn("o14", STRING)
                .setColumnSeparator(';')
                .setLineSeparator(System.getProperty("line.separator"))
                .setNullValue("-")
                .build();

        bind(CsvSchema.class).toInstance(csvSchema);
    }

    private class TotoDecimalModule extends DecimalModule {

        private DecimalFormat getDecimalFormat() {
            return super.decimalFormat;
        }
    }
}
