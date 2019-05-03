package com.example.training.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CurrencyUtils {

    private static final Pattern CURRENCY_CODE_PATTERN = Pattern.compile("(?:[\\d\\s]*)([A-Za-z]{3})");

    public static String parseCurrencyCode(String text) throws ParseException {
        requireNonNull(text, "text must not be null");

        Matcher currencyCodeMatcher = CURRENCY_CODE_PATTERN.matcher(text);
        String currencyCode;
        if (!currencyCodeMatcher.find() || (currencyCode = currencyCodeMatcher.group(1)) == null) {
            throw new ParseException(text, currencyCodeMatcher.start(1));
        }

        return currencyCode;
    }
}
