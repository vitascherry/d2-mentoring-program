package com.example.training.common.graphy;

import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.key.Key;
import com.example.training.graphy.linker.Linker;
import com.example.training.common.provider.CombinedPropertyProvider;
import com.example.training.common.provider.PropertyProvider;
import com.example.training.graphy.module.Module;

import java.util.List;

public class CombinedPropertyProviderModule implements Module {

    static final Key<PropertyProvider> PROPERTY_PROVIDER_KEY = Key.<PropertyProvider>builder()
            .withClass(PropertyProvider.class)
            .withName("combinedPropertyProvider")
            .build();

    @Override
    public void configure(Linker linker) {
        linker.install(PROPERTY_PROVIDER_KEY, linker1 -> {
            Factory<PropertyProvider> environmentPropertyProviderFactory = linker1.factoryFor(EnvironmentPropertyProviderModule.PROPERTY_PROVIDER_KEY);
            Factory<List<PropertyProvider>> overrideFilePropertyProvidersFactory = linker1.factoryFor(OverrideFilePropertyProvidersModule.PROPERTY_PROVIDERS_KEY);
            Factory<PropertyProvider> commonFilePropertyProviderFactory = linker1.factoryFor(CommonFilePropertyProviderModule.PROPERTY_PROVIDER_KEY);

            PropertyProvider environmentPropertyProvider = environmentPropertyProviderFactory.get(linker1);
            List<PropertyProvider> overrideFilePropertyProviders = overrideFilePropertyProvidersFactory.get(linker1);
            PropertyProvider commonFilePropertyProvider = commonFilePropertyProviderFactory.get(linker1);

            return CombinedPropertyProvider.builder()
                    .provider(environmentPropertyProvider)
                    .providers(overrideFilePropertyProviders)
                    .provider(commonFilePropertyProvider)
                    .build();
        });
    }
}
