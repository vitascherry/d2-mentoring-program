package com.example.training.common.graphy.module;

import com.example.training.graphy.key.Key;
import com.example.training.graphy.linker.Linker;
import com.example.training.common.provider.FilePropertyProvider;
import com.example.training.common.provider.PropertyProvider;
import com.example.training.graphy.module.Module;

import static com.example.training.common.constant.DefaultConstants.COMMON_PROPERTY_FILE;

public class CommonFilePropertyProviderModule implements Module {

    static final Key<PropertyProvider> PROPERTY_PROVIDER_KEY = Key.<PropertyProvider>builder()
            .withClass(PropertyProvider.class)
            .withName("commonFilePropertyProvider")
            .build();

    @Override
    public void configure(Linker linker) {
        linker.install(PROPERTY_PROVIDER_KEY, linker1 -> new FilePropertyProvider(COMMON_PROPERTY_FILE));
    }
}
