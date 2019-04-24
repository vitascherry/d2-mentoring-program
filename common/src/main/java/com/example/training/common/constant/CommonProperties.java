package com.example.training.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommonProperties {

    public static final String CONNECTION_TIME_TO_LIVE_MILLIS = "connectionTimeToLiveMillis";

    public static final String CONNECTION_TIMEOUT_MILLIS = "connectionTimeoutMillis";

    public static final String SOCKET_TIMEOUT_MILLIS = "socketTimeoutMillis";

    public static final String MAX_WAIT_TIMEOUT_MILLIS = "maxWaitTimeoutMillis";

    public static final String RETRY_LIMIT = "retryLimit";
}

