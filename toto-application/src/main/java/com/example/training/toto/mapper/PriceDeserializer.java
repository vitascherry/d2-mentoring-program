package com.example.training.toto.mapper;

import com.example.training.toto.domain.Price;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Currency;

import static com.example.training.common.util.CurrencyUtils.parseCurrencyCode;
import static java.math.BigDecimal.ZERO;

@Log4j2
@RequiredArgsConstructor
public class PriceDeserializer extends JsonDeserializer<Price> {

    private final DecimalFormat decimalFormatter;
    private Price zeroPrice = null;

    @Override
    public Price deserialize(JsonParser parser, DeserializationContext ctx) throws IOException {
        String text = parser.getText();

        try {
            String currencyCode = parseCurrencyCode(text);
            Currency currency = Currency.getInstance(currencyCode);
            init(currency);
            check(currency);
            decimalFormatter.applyPattern("###,### " + currencyCode);
            BigDecimal amount = (BigDecimal) decimalFormatter.parse(text);
            return new Price(amount, currency);

        } catch (ParseException e) {
            log.error("Error occurred while parsing Price from text '{}'. ErrorMessage: ", text, e);
            return zeroPrice;
        }
    }

    private void init(Currency currency) {
        if (zeroPrice == null) {
            zeroPrice = new Price(ZERO, currency);
            decimalFormatter.setCurrency(currency);
        }
    }

    private void check(Currency currency) {
        if (!decimalFormatter.getCurrency().equals(currency)) {
            log.error("Invalid currency passed '{}'", currency);
            throw new IllegalArgumentException("Currencies should match!");
        }
    }
}
