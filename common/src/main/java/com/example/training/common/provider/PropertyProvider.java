package com.example.training.common.provider;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface PropertyProvider {

    String getProperty(String propertyName);

    default String getProperty(String propertyName, Object... args) {
        return String.format(getProperty(propertyName), args);
    }

    default void setProperty(String key, String value) {
        throw new UnsupportedOperationException("Not implemented!");
    }

    default int getIntProperty(String propertyName, int defaultValue) {
        final String propertyValue = getProperty(propertyName);
        return PropertyTypeParser.parsePropertyWithDefaultValue(propertyValue, defaultValue, Integer::parseInt, Integer.class);
    }

    default long getLongProperty(String propertyName, long defaultValue) {
        final String propertyValue = getProperty(propertyName);
        return PropertyTypeParser.parsePropertyWithDefaultValue(propertyValue, defaultValue, Long::parseLong, Long.class);
    }

    default double getDoubleProperty(String propertyName, double defaultValue) {
        String propertyValue = getProperty(propertyName);
        return PropertyTypeParser.parsePropertyWithDefaultValue(propertyValue, defaultValue, Double::parseDouble, Double.class);
    }

    default LocalDate getLocalDateProperty(String propertyName) {
        String propertyValue = getProperty(propertyName);
        return PropertyTypeParser.parseProperty(propertyValue, LocalDate::parse, LocalDate.class);
    }

    default LocalDateTime getLocalDateTimeProperty(String propertyName) {
        String propertyValue = getProperty(propertyName);
        return PropertyTypeParser.parseProperty(propertyValue, LocalDateTime::parse, LocalDateTime.class);
    }
}
