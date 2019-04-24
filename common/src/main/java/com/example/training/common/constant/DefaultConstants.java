package com.example.training.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DefaultConstants {

    public static final String DEFAULT_DATE_FORMAT = "yyyMMdd";

    public static final int CONNECTION_TIMEOUT_MILLIS_DEFAULT = 250_000;

    public static final int CONNECTION_TIME_TO_LIVE_MILLIS_DEFAULT = 250_000;

    public static final int SOCKET_TIMEOUT_MILLIS_DEFAULT = 60_000;

    public static final int MAX_WAIT_TIMEOUT_MILLIS_DEFAULT = 1_000;

    public static final int RETRY_LIMIT_DEFAULT = 2;

    public static final String COMMON_PROPERTY_FILE = "common";

    public static final String PROPERTY_FILE = "override";

    public static final String TEST_PROPERTY_FILE = "test-override";
}
