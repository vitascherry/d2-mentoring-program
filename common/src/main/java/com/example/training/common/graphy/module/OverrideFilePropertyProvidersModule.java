package com.example.training.common.graphy.module;

import com.example.training.graphy.key.Key;
import com.example.training.graphy.linker.Linker;
import com.example.training.common.provider.PropertyProvider;
import com.example.training.graphy.module.Module;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public abstract class OverrideFilePropertyProvidersModule implements Module {

    private static final TypeReference<List<PropertyProvider>> LIST_TYPE_REFERENCE = new TypeReference<List<PropertyProvider>>() {};

    static final Key<List<PropertyProvider>> PROPERTY_PROVIDERS_KEY = Key.<List<PropertyProvider>>builder()
            .withTypeReference(LIST_TYPE_REFERENCE)
            .withName("overrideFilePropertyProviders")
            .build();

    @Override
    public void configure(Linker linker) {
        linker.install(PROPERTY_PROVIDERS_KEY, linker1 -> createOverrideFilePropertyProviders());
    }

    protected abstract List<PropertyProvider> createOverrideFilePropertyProviders();
}
