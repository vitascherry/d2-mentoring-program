package com.example.training.exchange.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExchangeConstants {

    public static final String NBU_API_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange";

    public static final String SYSTEM_CURRENCY_CODE = "UAH";

    public static final String EXCHANGE_OVERRIDE_PROPERTY_FILE = "exchange-override";
}
