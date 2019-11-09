package com.example.training.common.graphy;

import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import static com.fasterxml.jackson.dataformat.csv.CsvSchema.emptySchema;

public class CsvSchemaModule implements Module {

    @Override
    public void configure(Linker linker) {
        linker.install(CsvSchema.class, this::createCsvSchema);
    }

    protected CsvSchema createCsvSchema(Linker linker) {
        return emptySchema();
    }
}
