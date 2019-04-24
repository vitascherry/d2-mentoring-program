package com.example.training.common.guice;

import com.example.training.common.provider.CombinedPropertyProvider;
import com.example.training.common.provider.PropertyProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

public class CombinedPropertyProviderModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Provides
    @Singleton
    @Named("combinedPropertyProvider")
    public PropertyProvider combinedPropertyProvider(@Named("environmentPropertyProvider") PropertyProvider env,
                                                     @Named("overrideFilePropertyProvider") PropertyProvider overrideFile,
                                                     @Named("commonFilePropertyProvider") PropertyProvider commonFile) {
        return CombinedPropertyProvider.builder()
                .provider(env)
                .provider(overrideFile)
                .provider(commonFile)
                .build();
    }
}
