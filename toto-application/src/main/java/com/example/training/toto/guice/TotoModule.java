package com.example.training.toto.guice;

import com.example.training.common.guice.*;
import com.example.training.common.handler.Printer;
import com.example.training.common.handler.Reader;
import com.example.training.common.reader.CsvReader;
import com.example.training.common.service.DateTimeService;
import com.example.training.toto.handler.TotoConsoleHandler;
import com.example.training.toto.provider.RoundMockEntityProvider;
import com.example.training.toto.repository.TotoRepository;
import com.example.training.toto.repository.impl.MockTotoRepository;
import com.example.training.toto.service.TotoService;
import com.example.training.toto.service.impl.TotoServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import lombok.Getter;

import java.text.DecimalFormat;

@Getter
public class TotoModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new TotoDateTimeModule());
        install(new PrinterModule());
        install(new ReaderModule());

        DecimalModule decimalModule = new DecimalModule();
        install(decimalModule);

        CsvSchemaModule csvSchemaModule = new TotoCsvSchemaModule();
        install(csvSchemaModule);

        CsvMapperModule csvMapperModule = new TotoCsvMapperModule(decimalModule);
        install(csvMapperModule);

        install(new CsvReaderModule(csvSchemaModule, csvMapperModule));

        bind(TotoService.class).to(TotoServiceImpl.class);
        bind(TotoRepository.class).to(MockTotoRepository.class);
    }

    @Singleton
    @Provides
    public TotoConsoleHandler totoConsoleHandlerProvider(Printer printer, Reader reader, DecimalFormat decimalFormatter,
                                                         DateTimeService dateTimeService, TotoService totoService) {
        return TotoConsoleHandler.builder()
                .printer(printer)
                .reader(reader)
                .decimalFormatter(decimalFormatter)
                .dateTimeService(dateTimeService)
                .totoService(totoService)
                .build();
    }

    @Singleton
    @Provides
    public TotoServiceImpl totoServiceImplProvider(TotoRepository totoRepository, DecimalFormat decimalFormatter) {
        return new TotoServiceImpl(totoRepository, decimalFormatter);
    }

    @Singleton
    @Provides
    public MockTotoRepository mockTotoRepositoryProvider(RoundMockEntityProvider mockProvider) {
        return new MockTotoRepository(mockProvider);
    }

    @Singleton
    @Provides
    public RoundMockEntityProvider roundMockProvider(CsvReader csvReader) {
        return new RoundMockEntityProvider(csvReader);
    }
}
