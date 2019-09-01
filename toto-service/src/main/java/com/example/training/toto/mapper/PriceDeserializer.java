package com.example.training.toto.mapper;

import com.example.training.toto.domain.Price;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Currency;

import static com.example.training.common.util.CurrencyUtils.parseCurrencyCode;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;

@Log4j2
public class PriceDeserializer extends JsonDeserializer<Price> {

    private DecimalFormat decimalFormat;
    private Price zeroPrice;

    public PriceDeserializer() {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormatSymbols.setMonetaryDecimalSeparator('.');
        decimalFormatSymbols.setGroupingSeparator(' ');
        decimalFormat = new DecimalFormat("###,###.##", decimalFormatSymbols);
        decimalFormat.setRoundingMode(HALF_EVEN);
        decimalFormat.setParseBigDecimal(true);
    }

    @Override
    @SneakyThrows(ParseException.class)
    public Price deserialize(JsonParser parser, DeserializationContext ctx) throws IOException {
        String text = parser.getText();

        String currencyCode = parseCurrencyCode(text);
        Currency currency = Currency.getInstance(currencyCode);
        if (zeroPrice == null) {
            zeroPrice = new Price(ZERO, currency);
            decimalFormat.applyPattern("###,### " + currencyCode);
        }
        if (!zeroPrice.getCurrency().equals(currency)) {
            log.error("Invalid currency passed {}", currency);
            throw new IllegalStateException(String.format("Parsed currency %s should match %s!",
                    currency, zeroPrice.getCurrency()));
        }
        BigDecimal amount = (BigDecimal) decimalFormat.parse(text);
        return new Price(amount, zeroPrice.getCurrency());
    }
}
