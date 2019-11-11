package com.example.training.toto.graphy;

import com.example.training.common.graphy.DecimalFormatModule;
import com.example.training.graphy.factory.SingletonFactory;
import com.example.training.graphy.key.Key;
import com.example.training.graphy.linker.Linker;

import java.text.DecimalFormat;

public class TotoMockDecimalFormatModule extends DecimalFormatModule {

    static final Key DECIMAL_FORMAT_KEY = Key.builder()
            .type(DecimalFormat.class)
            .name("totoMockDecimalFormat")
            .build();

    @Override
    public void configure(Linker linker) {
        linker.install(DECIMAL_FORMAT_KEY, SingletonFactory.of(this::createDecimalFormat));
    }
}
