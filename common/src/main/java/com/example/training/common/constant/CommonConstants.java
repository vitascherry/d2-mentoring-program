package com.example.training.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommonConstants {

    public static final String DEFAULT_DATE_FORMAT = "yyyMMdd";

    public static final long CONNECTION_TIMEOUT_MILLIS = 250_000L;

    public static final int CONNECTION_TIME_TO_LIVE_MILLIS = 5_000;

    public static final int SOCKET_TIMEOUT_MILLIS = 60_000;
}
