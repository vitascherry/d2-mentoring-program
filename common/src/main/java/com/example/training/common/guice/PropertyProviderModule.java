package com.example.training.common.guice;

import com.example.training.common.provider.CombinedPropertyProvider;
import com.example.training.common.provider.EnvironmentPropertyProvider;
import com.example.training.common.provider.FilePropertyProvider;
import com.example.training.common.provider.PropertyProvider;
import com.example.training.common.wrappers.EnvironmentWrapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import java.util.List;

import static com.example.training.common.constant.DefaultConstants.COMMON_PROPERTY_FILE;

public abstract class PropertyProviderModule extends AbstractModule {

    @Override
    protected void configure() {
        PropertyProvider env = createEnvironmentPropertyProvider();
        bind(PropertyProvider.class).annotatedWith(Names.named("environmentPropertyProvider")).toInstance(env);

        PropertyProvider commonFile = createCommonFilePropertyProvider();
        bind(PropertyProvider.class).annotatedWith(Names.named("commonFilePropertyProvider")).toInstance(commonFile);

        List<PropertyProvider> overrideFiles = createOverrideFilePropertyProviders();
        bind(new TypeLiteral<List<PropertyProvider>>() {}).toInstance(overrideFiles);
    }

    protected PropertyProvider createEnvironmentPropertyProvider() {
        return new EnvironmentPropertyProvider(new EnvironmentWrapper());
    }

    protected PropertyProvider createCommonFilePropertyProvider() {
        return new FilePropertyProvider(COMMON_PROPERTY_FILE);
    }

    protected abstract List<PropertyProvider> createOverrideFilePropertyProviders();

    @Provides
    @Singleton
    @Named("combinedPropertyProvider")
    public PropertyProvider combinedPropertyProvider(@Named("environmentPropertyProvider") PropertyProvider env,
                                                     @Named("commonFilePropertyProvider") PropertyProvider commonFile,
                                                     List<PropertyProvider> overrideFiles) {
        return CombinedPropertyProvider.builder()
                .provider(env)
                .providers(overrideFiles)
                .provider(commonFile)
                .build();
    }
}
