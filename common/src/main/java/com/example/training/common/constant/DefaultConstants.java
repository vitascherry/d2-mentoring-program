package com.example.training.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DefaultConstants {

    public static final String DEFAULT_DATE_FORMAT = "yyyMMdd";

    public static final String COMMON_PROPERTY_FILE = "common";

    public static final String TEST_PROPERTY_FILE = "test-override";
}
