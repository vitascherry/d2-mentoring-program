package com.example.training.common.provider;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.function.Function;

@Log4j2
@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class PropertyTypeParser {

    static <T> T parsePropertyWithDefaultValue(String propertyValue, T defaultValue, Function<String, T> function, Class<T> clazz){
        try {
            return function.apply(propertyValue);
        } catch (Exception e) {
            log.warn("Can't parse property {} to {}. Default value will be used.", propertyValue, clazz);
            return defaultValue;
        }
    }

    static <T> T parseProperty(String propertyValue, Function<String, T> function, Class<T> clazz){
        return parsePropertyWithDefaultValue(propertyValue, null, function, clazz);
    }
}
