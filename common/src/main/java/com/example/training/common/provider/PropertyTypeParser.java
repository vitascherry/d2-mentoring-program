package com.example.training.common.provider;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

import java.util.function.Function;

@Log4j2
@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class PropertyTypeParser {

    static <T> T parsePropertyWithDefaultValue(String propertyValue, T defaultValue,
                                               @NonNull Function<String, T> foo, Class<T> clazz) {
        try {
            return foo.apply(propertyValue);
        } catch (Exception e) {
            log.warn("Can't parse property {} to {}. Default value will be used.", propertyValue, clazz);
            return defaultValue;
        }
    }

    static <T> T parseProperty(String propertyValue, Function<String, T> foo, Class<T> clazz) {
        return parsePropertyWithDefaultValue(propertyValue, null, foo, clazz);
    }
}
