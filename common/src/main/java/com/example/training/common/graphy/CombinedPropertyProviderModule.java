package com.example.training.common.graphy;

import com.example.training.common.provider.CombinedPropertyProvider;
import com.example.training.common.provider.PropertyProvider;
import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.factory.SingletonFactory;
import com.example.training.graphy.key.Key;
import com.example.training.graphy.key.Scope;
import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;

import java.util.List;

public class CombinedPropertyProviderModule implements Module {

    static final Key PROPERTY_PROVIDER_KEY = Key.builder()
            .type(PropertyProvider.class)
            .name("combinedPropertyProvider")
            .scope(Scope.SINGLETON)
            .build();

    @Override
    public void configure(Linker linker) {
        linker.install(PROPERTY_PROVIDER_KEY, SingletonFactory.of(this::createPropertyProvider));
    }

    protected PropertyProvider createPropertyProvider(Linker linker) {
        Factory<PropertyProvider> environmentPropertyProviderFactory = linker.factoryFor(EnvironmentPropertyProviderModule.PROPERTY_PROVIDER_KEY);
        Factory<List<PropertyProvider>> overrideFilePropertyProvidersFactory = linker.factoryFor(OverrideFilePropertyProvidersModule.PROPERTY_PROVIDERS_KEY);
        Factory<PropertyProvider> commonFilePropertyProviderFactory = linker.factoryFor(CommonFilePropertyProviderModule.PROPERTY_PROVIDER_KEY);

        PropertyProvider environmentPropertyProvider = environmentPropertyProviderFactory.get(linker);
        List<PropertyProvider> overrideFilePropertyProviders = overrideFilePropertyProvidersFactory.get(linker);
        PropertyProvider commonFilePropertyProvider = commonFilePropertyProviderFactory.get(linker);

        return CombinedPropertyProvider.builder()
                .provider(environmentPropertyProvider)
                .providers(overrideFilePropertyProviders)
                .provider(commonFilePropertyProvider)
                .build();
    }
}
