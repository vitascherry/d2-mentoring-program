package com.example.training.common.graphy;

import com.example.training.common.provider.EnvironmentPropertyProvider;
import com.example.training.common.provider.PropertyProvider;
import com.example.training.common.wrappers.EnvironmentWrapper;
import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.factory.SingletonFactory;
import com.example.training.graphy.key.Key;
import com.example.training.graphy.key.Scope;
import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;

public class EnvironmentPropertyProviderModule implements Module {

    static final Key PROPERTY_PROVIDER_KEY = Key.builder()
            .type(PropertyProvider.class)
            .name("environmentPropertyProvider")
            .scope(Scope.SINGLETON)
            .build();

    @Override
    public void configure(Linker linker) {
        linker.install(EnvironmentWrapper.class, SingletonFactory.of(this::createEnvironmentWrapper));

        linker.install(PROPERTY_PROVIDER_KEY, SingletonFactory.of(this::createPropertyProvider));
    }

    protected EnvironmentWrapper createEnvironmentWrapper(Linker linker) {
        return new EnvironmentWrapper();
    }

    protected PropertyProvider createPropertyProvider(Linker linker) {
        Factory<EnvironmentWrapper> wrapperFactory = linker.factoryFor(EnvironmentWrapper.class);
        EnvironmentWrapper environmentWrapper = wrapperFactory.get(linker);
        return new EnvironmentPropertyProvider(environmentWrapper);
    }
}
