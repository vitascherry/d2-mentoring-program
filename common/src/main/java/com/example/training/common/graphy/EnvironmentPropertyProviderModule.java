package com.example.training.common.graphy;

import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.key.Key;
import com.example.training.graphy.linker.Linker;
import com.example.training.common.provider.EnvironmentPropertyProvider;
import com.example.training.common.provider.PropertyProvider;
import com.example.training.common.wrappers.EnvironmentWrapper;
import com.example.training.graphy.module.Module;

public class EnvironmentPropertyProviderModule implements Module {

    static final Key<PropertyProvider> PROPERTY_PROVIDER_KEY = Key.<PropertyProvider>builder()
            .withClass(PropertyProvider.class)
            .withName("environmentPropertyProvider")
            .build();

    @Override
    public void configure(Linker linker) {
        linker.install(EnvironmentWrapper.class, linker1 -> new EnvironmentWrapper());

        linker.install(PROPERTY_PROVIDER_KEY, linker1 -> {
            Factory<EnvironmentWrapper> wrapperFactory = linker1.factoryFor(EnvironmentWrapper.class);
            EnvironmentWrapper environmentWrapper = wrapperFactory.get(linker1);
            return new EnvironmentPropertyProvider(environmentWrapper);
        });
    }
}
