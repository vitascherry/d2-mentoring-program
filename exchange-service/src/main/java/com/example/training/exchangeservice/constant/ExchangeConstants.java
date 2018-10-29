package com.example.training.exchangeservice.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExchangeConstants {

    public static final String DATE_FORMAT = "dd.MM.yyyy";

    public static final String NBU_API_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange";

    public static final long CONNECTION_TIMEOUT_MILLIS = 250_000L;

    public static final String SYSTEM_CURRENCY_CODE = "UAH";
}
