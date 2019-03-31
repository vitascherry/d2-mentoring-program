package com.example.training.exchangeservice.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExchangeConstants {

    public static final String DATE_FORMAT = "dd.MM.yyyy";

    public static final String NBU_API_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange";

    public static final String SYSTEM_CURRENCY_CODE = "UAH";

    public static final int EXCHANGE_RATE_CLIENT_RETRY_LIMIT = 2;

    public static final int EXCHANGE_RATE_CLIENT_MAX_WAIT_TIMEOUT_MILLIS = 1_000;
}
