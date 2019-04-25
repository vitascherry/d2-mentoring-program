package com.example.training.clientcommon.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ClientConstants {

    public static final int CONNECTION_TIMEOUT_MILLIS_DEFAULT = 250_000;

    public static final int CONNECTION_TIME_TO_LIVE_MILLIS_DEFAULT = 250_000;

    public static final int SOCKET_TIMEOUT_MILLIS_DEFAULT = 60_000;

    public static final int MAX_WAIT_TIMEOUT_MILLIS_DEFAULT = 1_000;

    public static final int RETRY_LIMIT_DEFAULT = 2;

    public static final String CLIENT_COMMON_PROPERTY_FILE = "client-common";
}
