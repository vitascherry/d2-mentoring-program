package com.example.training.common.graphy;

import com.example.training.common.provider.FilePropertyProvider;
import com.example.training.common.provider.PropertyProvider;
import com.example.training.graphy.factory.SingletonFactory;
import com.example.training.graphy.key.Key;
import com.example.training.graphy.key.Scope;
import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;

import static com.example.training.common.constant.DefaultConstants.COMMON_PROPERTY_FILE;

public class CommonFilePropertyProviderModule implements Module {

    static final Key PROPERTY_PROVIDER_KEY = Key.builder()
            .type(PropertyProvider.class)
            .name("commonFilePropertyProvider")
            .scope(Scope.SINGLETON)
            .build();

    @Override
    public void configure(Linker linker) {
        linker.install(PROPERTY_PROVIDER_KEY, SingletonFactory.of(this::createPropertyProvider));
    }

    protected PropertyProvider createPropertyProvider(Linker linker) {
        return new FilePropertyProvider(COMMON_PROPERTY_FILE);
    }
}
