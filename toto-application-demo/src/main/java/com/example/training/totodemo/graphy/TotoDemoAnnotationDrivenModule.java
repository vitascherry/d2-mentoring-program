package com.example.training.totodemo.graphy;

import com.example.training.common.mapper.EntityMapper;
import com.example.training.consolecommon.graphy.ConsoleCommonModule;
import com.example.training.consolecommon.handler.Handler;
import com.example.training.consolecommon.handler.Printer;
import com.example.training.consolecommon.handler.Reader;
import com.example.training.graphy.annotation.Import;
import com.example.training.graphy.annotation.Provides;
import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.AnnotationDrivenModule;
import com.example.training.graphy.proxy.Proxy;
import com.example.training.toto.domain.Outcome;
import com.example.training.toto.domain.OutcomeSet;
import com.example.training.toto.graphy.TotoAggregateModule;
import com.example.training.toto.service.TotoService;
import com.example.training.totodemo.graphy.aop.ValidateArraySizeInvocationHandler;
import com.example.training.totodemo.handler.TotoDemoHandler;
import com.example.training.totodemo.mapper.OutcomeSetMapper;

import javax.inject.Singleton;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

import static com.example.training.totodemo.graphy.TotoDemoModule.ENTITY_MAPPER_TYPE_REFERENCE;

@Import({
        ConsoleCommonModule.class,
        TotoDemoDateTimeModule.class,
        TotoDemoDecimalModule.class,
        TotoAggregateModule.class
})
public class TotoDemoAnnotationDrivenModule extends AnnotationDrivenModule {

    @Provides
    @Singleton
    public EntityMapper<Outcome[], OutcomeSet> createOutcomeSetMapper(Linker linker) {
        // Using simple AOP to verify outcomes array size
        return Proxy.of(new ValidateArraySizeInvocationHandler<>(new OutcomeSetMapper()), OutcomeSetMapper.class);
    }

    @Provides
    @Singleton
    public Handler createTotoConsoleHandler(Linker linker) {
        Factory<Printer> printerFactory = linker.factoryFor(Printer.class);
        Factory<Reader> readerFactory = linker.factoryFor(Reader.class);
        Factory<TotoService> totoServiceFactory = linker.factoryFor(TotoService.class);
        Factory<EntityMapper<Outcome[], OutcomeSet>> outcomeSetMapperFactory = linker.factoryFor(ENTITY_MAPPER_TYPE_REFERENCE.getType());
        Factory<DecimalFormat> decimalFormatFactory = linker.factoryFor(TotoDemoDecimalModule.DECIMAL_FORMAT_KEY);
        Factory<DateTimeFormatter> dateTimeFormatterFactory = linker.factoryFor(TotoDemoDateTimeModule.DATE_TIME_FORMATTER_KEY);

        Printer printer = printerFactory.get(linker);
        Reader reader = readerFactory.get(linker);
        TotoService totoService = totoServiceFactory.get(linker);
        EntityMapper<Outcome[], OutcomeSet> outcomeSetMapper = outcomeSetMapperFactory.get(linker);
        DecimalFormat decimalFormat = decimalFormatFactory.get(linker);
        DateTimeFormatter dateTimeFormatter = dateTimeFormatterFactory.get(linker);

        return TotoDemoHandler.builder()
                .printer(printer)
                .reader(reader)
                .decimalFormat(decimalFormat)
                .dateTimeFormatter(dateTimeFormatter)
                .totoService(totoService)
                .outcomeSetMapper(outcomeSetMapper)
                .build();
    }
}
