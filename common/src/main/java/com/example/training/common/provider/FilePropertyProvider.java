package com.example.training.common.provider;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.MissingResourceException;

import static java.util.ResourceBundle.getBundle;

@Log4j2
@AllArgsConstructor
public class FilePropertyProvider implements PropertyProvider {

    private final String fileName;

    public String getProperty(String propertyName) {
        try {
            return getBundle(fileName).getString(propertyName);
        } catch (MissingResourceException e) {
            log.warn("Resource bundle {} or key {} within the bundle is not found!", fileName, propertyName);
        }
        return null;
    }
}

