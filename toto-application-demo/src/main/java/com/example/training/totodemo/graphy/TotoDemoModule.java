package com.example.training.totodemo.graphy;

import com.example.training.common.mapper.EntityMapper;
import com.example.training.consolecommon.graphy.ConsoleCommonModule;
import com.example.training.consolecommon.handler.Handler;
import com.example.training.consolecommon.handler.Printer;
import com.example.training.consolecommon.handler.Reader;
import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.factory.SingletonFactory;
import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;
import com.example.training.graphy.proxy.Proxy;
import com.example.training.toto.dto.OutcomeDto;
import com.example.training.toto.dto.OutcomeSetDto;
import com.example.training.toto.graphy.TotoAggregateModule;
import com.example.training.toto.service.TotoService;
import com.example.training.totodemo.graphy.interceptors.ValidateReturnValueInvocationHandler;
import com.example.training.totodemo.handler.TotoDemoHandler;
import com.example.training.totodemo.mapper.OutcomeSetDtoMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public class TotoDemoModule implements Module {

    static final TypeReference<EntityMapper<OutcomeDto[], OutcomeSetDto>> ENTITY_MAPPER_TYPE_REFERENCE = new TypeReference<EntityMapper<OutcomeDto[], OutcomeSetDto>>() {};

    @Override
    public void configure(Linker linker) {
        linker.install(ENTITY_MAPPER_TYPE_REFERENCE.getType(), SingletonFactory.of(this::createOutcomeSetDtoMapper));
        linker.install(Handler.class, SingletonFactory.of(this::createTotoConsoleHandler));

        new ConsoleCommonModule().configure(linker);
        new TotoDemoDateTimeModule().configure(linker);
        new TotoDemoDecimalModule().configure(linker);
        new TotoAggregateModule().configure(linker);
    }

    protected EntityMapper<OutcomeDto[], OutcomeSetDto> createOutcomeSetDtoMapper(Linker linker) {
        // Using JDK proxy to intercept OutcomeSetDtoMapper.map(Outcome[]) for return value validation
        return Proxy.of(new ValidateReturnValueInvocationHandler<>(new OutcomeSetDtoMapper()), OutcomeSetDtoMapper.class);
    }

    protected Handler createTotoConsoleHandler(Linker linker) {
        Factory<Printer> printerFactory = linker.factoryFor(Printer.class);
        Factory<Reader> readerFactory = linker.factoryFor(Reader.class);
        Factory<TotoService> totoServiceFactory = linker.factoryFor(TotoService.class);
        Factory<EntityMapper<OutcomeDto[], OutcomeSetDto>> outcomeSetMapperFactory = linker.factoryFor(ENTITY_MAPPER_TYPE_REFERENCE.getType());
        Factory<DecimalFormat> decimalFormatFactory = linker.factoryFor(TotoDemoDecimalModule.DECIMAL_FORMAT_KEY);
        Factory<DateTimeFormatter> dateTimeFormatterFactory = linker.factoryFor(TotoDemoDateTimeModule.DATE_TIME_FORMATTER_KEY);

        Printer printer = printerFactory.get(linker);
        Reader reader = readerFactory.get(linker);
        TotoService totoService = totoServiceFactory.get(linker);
        EntityMapper<OutcomeDto[], OutcomeSetDto> outcomeSetMapper = outcomeSetMapperFactory.get(linker);
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
