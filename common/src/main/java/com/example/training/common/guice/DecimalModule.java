package com.example.training.common.guice;

import com.google.inject.AbstractModule;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import static java.math.RoundingMode.HALF_EVEN;

public class DecimalModule extends AbstractModule {

    protected DecimalFormatSymbols decimalFormatSymbols;
    protected DecimalFormat decimalFormat;

    @Override
    protected void configure() {
        bindDecimalFormatSymbols();
        bindDecimalFormat();
    }

    protected void bindDecimalFormatSymbols() {
        decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormatSymbols.setMonetaryDecimalSeparator('.');
        decimalFormatSymbols.setGroupingSeparator(' ');

        bind(DecimalFormatSymbols.class).toInstance(decimalFormatSymbols);
    }

    protected void bindDecimalFormat() {
        decimalFormat = new DecimalFormat("###,###.##", decimalFormatSymbols);
        decimalFormat.setRoundingMode(HALF_EVEN);
        decimalFormat.setParseBigDecimal(true);

        bind(DecimalFormat.class).toInstance(decimalFormat);
    }
}
