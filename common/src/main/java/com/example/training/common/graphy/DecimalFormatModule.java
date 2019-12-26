package com.example.training.common.graphy;

import com.example.training.graphy.factory.SingletonFactory;
import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import static java.math.RoundingMode.HALF_EVEN;

public class DecimalFormatModule implements Module {

    @Override
    public void configure(Linker linker) {
        linker.install(DecimalFormat.class, SingletonFactory.of(this::createDecimalFormat));
    }

    private DecimalFormatSymbols createDecimalFormatSymbols() {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormatSymbols.setMonetaryDecimalSeparator('.');
        decimalFormatSymbols.setGroupingSeparator(' ');
        return decimalFormatSymbols;
    }

    protected DecimalFormat createDecimalFormat(Linker linker) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###.##", createDecimalFormatSymbols());
        decimalFormat.setRoundingMode(HALF_EVEN);
        decimalFormat.setParseBigDecimal(true);
        return decimalFormat;
    }
}
