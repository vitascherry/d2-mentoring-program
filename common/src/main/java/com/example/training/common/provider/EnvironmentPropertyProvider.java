package com.example.training.common.provider;

import com.example.training.common.wrappers.EnvironmentWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public class EnvironmentPropertyProvider implements PropertyProvider {

    private final EnvironmentWrapper delegate;

    @Override
    public String getProperty(String propertyName) {
        String envProperty = delegate.getProperty(propertyName);
        if (envProperty == null) {
            log.warn("Can't find environment variable {}", propertyName);
        }
        return envProperty;
    }
}
