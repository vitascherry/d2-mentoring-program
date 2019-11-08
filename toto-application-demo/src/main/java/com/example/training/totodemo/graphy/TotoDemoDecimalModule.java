package com.example.training.totodemo.graphy;

import com.example.training.graphy.key.Key;
import com.example.training.graphy.linker.Linker;
import com.example.training.common.graphy.module.DecimalFormatModule;

import java.text.DecimalFormat;

public class TotoDemoDecimalModule extends DecimalFormatModule {

    static final Key<DecimalFormat> DECIMAL_FORMAT_KEY = Key.<DecimalFormat>builder()
            .withClass(DecimalFormat.class)
            .withName("totoDemoDecimalFormat")
            .build();

    @Override
    public void configure(Linker linker) {
        linker.install(DECIMAL_FORMAT_KEY, this::createDecimalFormat);
    }
}
