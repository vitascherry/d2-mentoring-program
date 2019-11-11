package com.example.training.totodemo.graphy;

import com.example.training.common.mapper.EntityMapper;
import com.example.training.common.util.FunctionUtils;
import com.example.training.consolecommon.graphy.ConsoleCommonModule;
import com.example.training.consolecommon.handler.Handler;
import com.example.training.consolecommon.handler.Printer;
import com.example.training.consolecommon.handler.Reader;
import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.factory.SingletonFactory;
import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;
import com.example.training.toto.domain.Outcome;
import com.example.training.toto.domain.OutcomeSet;
import com.example.training.toto.graphy.TotoAggregateModule;
import com.example.training.toto.service.TotoService;
import com.example.training.totodemo.handler.TotoDemoHandler;
import com.example.training.totodemo.mapper.OutcomeSetMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.apache.logging.log4j.LogManager.getLogger;

public class TotoDemoModule implements Module {

    static final TypeReference<EntityMapper<Outcome[], OutcomeSet>> ENTITY_MAPPER_TYPE_REFERENCE = new TypeReference<EntityMapper<Outcome[], OutcomeSet>>() {};

    @Override
    public void configure(Linker linker) {
        linker.install(ENTITY_MAPPER_TYPE_REFERENCE.getType(), SingletonFactory.of(this::createOutcomeSetMapper));
        linker.install(Handler.class, SingletonFactory.of(this::createTotoConsoleHandler));

        new ConsoleCommonModule().configure(linker);
        new TotoDemoDateTimeModule().configure(linker);
        new TotoDemoDecimalModule().configure(linker);
        new TotoAggregateModule().configure(linker);
    }

    protected EntityMapper<Outcome[], OutcomeSet> createOutcomeSetMapper(Linker linker) {
        // Using plain java to intercept call to map method to log invocation and verify outcomes array size
        return entity -> FunctionUtils.wrap((Outcome[] arg) -> {
            getLogger(OutcomeSetMapper.class).info("Calling OutcomeSetMapper.map(Outcome[]) with args: {}", Arrays.toString(arg));
            return arg;
        }).andThen((Outcome[] arg) -> {
            if (arg.length != 14) throw new IllegalArgumentException("Games count in outcomes should be 14");
            return arg;
        }).andThen(new OutcomeSetMapper()::map).apply(entity);
    }

    protected Handler createTotoConsoleHandler(Linker linker) {
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
