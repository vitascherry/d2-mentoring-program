package com.example.training.toto.mapper;

import com.example.training.toto.dto.PriceDto;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
public class PriceDtoDeserializer extends JsonDeserializer<PriceDto> {

    private final DecimalFormat decimalFormat;
    private PriceDto zeroPrice;

    @Override
    @SneakyThrows(ParseException.class)
    public PriceDto deserialize(JsonParser parser, DeserializationContext ctx) throws IOException {
        String text = parser.getText();

        String currencyCode = parseCurrencyCode(text);
        Currency currency = Currency.getInstance(currencyCode);
        if (zeroPrice == null) {
            zeroPrice = new PriceDto(ZERO, currency);
            decimalFormat.applyPattern("###,### " + currencyCode);
        }
        if (!zeroPrice.getCurrency().equals(currency)) {
            log.error("Invalid currency passed {}", currency);
            throw new IllegalStateException(String.format("Parsed currency %s should match %s!",
                    currency, zeroPrice.getCurrency()));
        }
        BigDecimal amount = (BigDecimal) decimalFormat.parse(text);
        return new PriceDto(amount, zeroPrice.getCurrency());
    }
}
