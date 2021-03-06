package com.example.training.sportsbettingdemo.guice;

import com.example.training.common.guice.DecimalModule;
import com.google.inject.name.Names;

import java.text.DecimalFormat;

public class SportsBettingDemoDecimalModule extends DecimalModule {

    @Override
    protected void configure() {
        DecimalFormat decimalFormat = createDecimalFormat();
        bind(DecimalFormat.class).annotatedWith(Names.named("sportsBettingDemoDecimalFormat")).toInstance(decimalFormat);
    }
}
