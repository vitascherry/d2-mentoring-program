package com.example.training.totodemo.graphy;

import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;
import com.example.training.consolecommon.handler.Handler;
import com.example.training.consolecommon.handler.Printer;
import com.example.training.consolecommon.handler.Reader;
import com.example.training.toto.service.TotoService;
import com.example.training.totodemo.guice.mapper.OutcomeSetMapper;
import com.example.training.totodemo.handler.TotoDemoHandler;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public class TotoDemoModule implements Module {

    @Override
    public void configure(Linker linker) {
        // TODO
    }

    public Handler totoConsoleHandlerProvider(Linker linker) {
        Printer printer = null;
        Reader reader = null;
        TotoService totoService = null;
        OutcomeSetMapper outcomeSetMapper = null;
        DecimalFormat decimalFormat = null;
        DateTimeFormatter dateTimeFormatter = null;

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
