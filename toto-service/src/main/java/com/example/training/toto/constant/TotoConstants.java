package com.example.training.toto.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Currency;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TotoConstants {

    public static final String DATE_FORMAT = "yyyy.MM.dd.";

    public static final String TOT_CURRENCY_CODE = "UAH";

    public static final Currency TOTO_CURRENCY = Currency.getInstance(TOT_CURRENCY_CODE);
}
