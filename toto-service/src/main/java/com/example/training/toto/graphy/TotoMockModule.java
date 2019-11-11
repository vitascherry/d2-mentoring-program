package com.example.training.toto.graphy;

import com.example.training.common.provider.EntityProvider;
import com.example.training.common.reader.CsvReader;
import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.factory.SingletonFactory;
import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;
import com.example.training.toto.domain.Round;
import com.example.training.toto.provider.RoundMockEntityProvider;

import java.time.LocalDate;

import static com.example.training.toto.graphy.TotoAggregateModule.ENTITY_PROVIDER_TYPE_REFERENCE;

public class TotoMockModule implements Module {

    @Override
    public void configure(Linker linker) {
        new TotoMockCsvReaderModule().configure(linker);

        linker.install(ENTITY_PROVIDER_TYPE_REFERENCE.getType(), SingletonFactory.of(this::createRoundEntityProvider));
    }

    protected EntityProvider<LocalDate, Round> createRoundEntityProvider(Linker linker) {
        Factory<CsvReader> csvReaderFactory = linker.factoryFor(TotoMockCsvReaderModule.CSV_READER_KEY);
        CsvReader csvReader = csvReaderFactory.get(linker);
        return new RoundMockEntityProvider(csvReader);
    }
}
