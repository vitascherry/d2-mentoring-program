package com.example.training.common.provider;

import lombok.Builder;
import lombok.Singular;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Objects;

@Log4j2
@Builder
public class CombinedPropertyProvider implements PropertyProvider {

    @Singular("provider")
    private List<PropertyProvider> providers;

    public String getProperty(String propertyName) {
        return providers.stream()
                .map(provider -> provider.getProperty(propertyName))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }
}
