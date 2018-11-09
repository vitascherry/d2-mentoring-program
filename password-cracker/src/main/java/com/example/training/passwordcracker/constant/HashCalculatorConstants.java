package com.example.training.passwordcracker.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HashCalculatorConstants {

    public static final long PASSWORD_CALCULATION_TIMEOUT_MIN = 10L;

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
}
