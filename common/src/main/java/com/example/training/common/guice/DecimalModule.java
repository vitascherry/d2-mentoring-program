package com.example.training.common.guice;

import com.google.inject.AbstractModule;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import static java.math.RoundingMode.HALF_EVEN;

public class DecimalModule extends AbstractModule {

    @Override
    protected void configure() {
        DecimalFormat decimalFormat = createDecimalFormat();
        bind(DecimalFormat.class).toInstance(decimalFormat);
    }

    protected DecimalFormatSymbols createDecimalFormatSymbols() {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormatSymbols.setMonetaryDecimalSeparator('.');
        decimalFormatSymbols.setGroupingSeparator(' ');
        return decimalFormatSymbols;
    }

    protected DecimalFormat createDecimalFormat() {
        DecimalFormatSymbols decimalFormatSymbols = createDecimalFormatSymbols();

        DecimalFormat decimalFormat = new DecimalFormat("###,###.##", decimalFormatSymbols);
        decimalFormat.setRoundingMode(HALF_EVEN);
        decimalFormat.setParseBigDecimal(true);
        return decimalFormat;
    }
}
