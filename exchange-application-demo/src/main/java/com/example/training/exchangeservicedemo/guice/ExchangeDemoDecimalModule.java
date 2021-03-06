package com.example.training.exchangeservicedemo.guice;

import com.example.training.common.guice.DecimalModule;
import com.google.inject.name.Names;

import java.text.DecimalFormat;

public class ExchangeDemoDecimalModule extends DecimalModule {

    @Override
    protected void configure() {
        DecimalFormat decimalFormat = createDecimalFormat();
        bind(DecimalFormat.class).annotatedWith(Names.named("exchangeDemoDecimalFormat")).toInstance(decimalFormat);
    }
}
