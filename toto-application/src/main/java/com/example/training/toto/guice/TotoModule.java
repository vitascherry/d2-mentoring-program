package com.example.training.toto.guice;

import com.example.training.common.guice.CsvReaderModule;
import com.example.training.common.guice.DecimalModule;
import com.example.training.common.guice.PrinterModule;
import com.example.training.common.guice.ReaderModule;
import com.example.training.common.handler.Handler;
import com.example.training.common.handler.Printer;
import com.example.training.common.handler.Reader;
import com.example.training.common.reader.CsvReader;
import com.example.training.common.service.DateTimeService;
import com.example.training.toto.aop.ValidateOutcomesInterceptor;
import com.example.training.toto.aop.annotation.ValidateOutcomes;
import com.example.training.toto.domain.OutcomeSet;
import com.example.training.toto.guice.provider.OutcomeSetMapper;
import com.example.training.toto.handler.TotoConsoleHandler;
import com.example.training.toto.provider.RoundMockEntityProvider;
import com.example.training.toto.repository.TotoRepository;
import com.example.training.toto.repository.impl.MockTotoRepository;
import com.example.training.toto.service.TotoService;
import com.example.training.toto.service.impl.TotoServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;

import java.text.DecimalFormat;

public class TotoModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Handler.class).to(TotoConsoleHandler.class);
        bind(TotoService.class).to(TotoServiceImpl.class);
        bind(TotoRepository.class).to(MockTotoRepository.class);

        bindInterceptor(Matchers.any(), Matchers.annotatedWith(ValidateOutcomes.class), new ValidateOutcomesInterceptor());
        bind(OutcomeSet.class).toProvider(OutcomeSetMapper.class);

        install(new TotoDateTimeModule());
        install(new PrinterModule());
        install(new ReaderModule());
        install(new DecimalModule());
        install(new TotoCsvSchemaModule());
        install(new TotoCsvMapperModule());
        install(new CsvReaderModule());
    }

    @Singleton
    @Provides
    public TotoConsoleHandler totoConsoleHandlerProvider(Printer printer, Reader reader, DecimalFormat decimalFormatter,
                                                         DateTimeService dateTimeService, TotoService totoService,
                                                         OutcomeSetMapper outcomeSetMapper) {
        return TotoConsoleHandler.builder()
                .printer(printer)
                .reader(reader)
                .decimalFormatter(decimalFormatter)
                .dateTimeService(dateTimeService)
                .totoService(totoService)
                .outcomeSetMapper(outcomeSetMapper)
                .build();
    }

    @Singleton
    @Provides
    public TotoServiceImpl totoServiceImplProvider(TotoRepository totoRepository) {
        return new TotoServiceImpl(totoRepository);
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
