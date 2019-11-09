package com.example.training.common.graphy;

import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;

import java.time.format.DateTimeFormatter;

import static com.example.training.common.constant.DefaultConstants.DEFAULT_DATE_FORMAT;

public class DateTimeModule implements Module {

    @Override
    public void configure(Linker linker) {
        linker.install(DateTimeFormatter.class, this::createDateTimeFormatter);
    }

    protected DateTimeFormatter createDateTimeFormatter(Linker linker) {
        return DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
    }
}
