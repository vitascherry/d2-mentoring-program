package com.example.training.common.guice;

import com.example.training.common.provider.FilePropertyProvider;
import com.example.training.common.provider.PropertyProvider;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import static com.example.training.common.constant.DefaultConstants.COMMON_PROPERTY_FILE;
import static com.example.training.common.constant.DefaultConstants.PROPERTY_FILE;

public class FilePropertyProviderModule extends AbstractModule {

    @Override
    protected void configure() {
        PropertyProvider commonFile = createCommonFilePropertyProvider();
        bind(PropertyProvider.class).annotatedWith(Names.named("commonFilePropertyProvider")).toInstance(commonFile);

        PropertyProvider overrideFile = createOverrideFilePropertyProvider();
        bind(PropertyProvider.class).annotatedWith(Names.named("overrideFilePropertyProvider")).toInstance(overrideFile);
    }

    protected PropertyProvider createCommonFilePropertyProvider() {
        return new FilePropertyProvider(COMMON_PROPERTY_FILE);
    }

    protected PropertyProvider createOverrideFilePropertyProvider() {
        return new FilePropertyProvider(PROPERTY_FILE);
    }
}
