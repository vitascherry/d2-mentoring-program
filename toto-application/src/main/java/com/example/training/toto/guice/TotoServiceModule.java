package com.example.training.toto.guice;

import com.example.training.common.reader.CsvReader;
import com.example.training.common.service.DateTimeService;
import com.example.training.toto.controller.TotoController;
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
public class TotoServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new TotoDateTimeModule());
        install(new TotoCsvReaderModule());

        bind(TotoService.class).to(TotoServiceImpl.class);
        bind(TotoRepository.class).to(MockTotoRepository.class);
    }

    @Singleton
    @Provides
    public TotoController totoControllerProvider(DecimalFormat decimalFormatter,
                                                 DateTimeService dateTimeService,
                                                 TotoService totoService) {
        return TotoController.builder()
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
