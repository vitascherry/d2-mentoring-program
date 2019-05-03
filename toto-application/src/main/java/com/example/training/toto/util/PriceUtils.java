package com.example.training.toto.util;

import com.example.training.toto.domain.Round;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Currency;

import static java.util.Objects.requireNonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PriceUtils {

    public static Currency extractCurrencyOrDefault(Round round, Currency defaultValue) {
        requireNonNull(round, "round must not be null");

        return round.getPriceOf10Hits() != null ?
                round.getPriceOf10Hits().getCurrency() : round.getPriceOf11Hits() != null ?
                round.getPriceOf11Hits().getCurrency() : round.getPriceOf12Hits() != null ?
                round.getPriceOf12Hits().getCurrency() : round.getPriceOf13Hits() != null ?
                round.getPriceOf13Hits().getCurrency() : round.getPriceOf14Hits() != null ?
                round.getPriceOf14Hits().getCurrency() : defaultValue;
    }
}
