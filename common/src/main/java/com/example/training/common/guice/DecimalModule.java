package com.example.training.common.guice;

import com.google.inject.AbstractModule;
import lombok.Getter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import static java.math.RoundingMode.HALF_EVEN;

@Getter
public class DecimalModule extends AbstractModule {

    protected DecimalFormatSymbols decimalFormatSymbols;

    @Override
    protected void configure() {
        decimalFormatSymbols = createDecimalFormatSymbols();
        bind(DecimalFormatSymbols.class).toInstance(decimalFormatSymbols);

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
        DecimalFormat decimalFormat = new DecimalFormat("###,###.##", decimalFormatSymbols);
        decimalFormat.setRoundingMode(HALF_EVEN);
        decimalFormat.setParseBigDecimal(true);
        return decimalFormat;
    }
}
