package com.example.training.toto.guice;

import com.example.training.common.guice.CsvSchemaModule;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import static com.fasterxml.jackson.dataformat.csv.CsvSchema.ColumnType.NUMBER;
import static com.fasterxml.jackson.dataformat.csv.CsvSchema.ColumnType.STRING;

public class TotoCsvSchemaModule extends CsvSchemaModule {

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
}
