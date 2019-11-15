package com.example.training.toto.util;

import com.example.training.toto.domain.Round;
import com.example.training.toto.dto.RoundDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Currency;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CurrencyUtils {

    public static Currency extractCurrencyOrDefault(@NonNull Round round, Currency defaultValue) {
        return round.getPriceOf10Hits() != null ? round.getPriceOf10Hits().getCurrency() :
                round.getPriceOf11Hits() != null ? round.getPriceOf11Hits().getCurrency() :
                        round.getPriceOf12Hits() != null ? round.getPriceOf12Hits().getCurrency() :
                                round.getPriceOf13Hits() != null ? round.getPriceOf13Hits().getCurrency() :
                                        round.getPriceOf14Hits() != null ? round.getPriceOf14Hits().getCurrency() :
                                                defaultValue;
    }

    public static Currency extractCurrencyOrDefault(@NonNull RoundDto roundDto, Currency defaultValue) {
        return roundDto.getPriceOf10Hits() != null ? roundDto.getPriceOf10Hits().getCurrency() :
                roundDto.getPriceOf11Hits() != null ? roundDto.getPriceOf11Hits().getCurrency() :
                        roundDto.getPriceOf12Hits() != null ? roundDto.getPriceOf12Hits().getCurrency() :
                                roundDto.getPriceOf13Hits() != null ? roundDto.getPriceOf13Hits().getCurrency() :
                                        roundDto.getPriceOf14Hits() != null ? roundDto.getPriceOf14Hits().getCurrency() :
                                                defaultValue;
    }
}
