package com.example.training.common.guice;

import com.example.training.common.provider.EnvironmentPropertyProvider;
import com.example.training.common.provider.PropertyProvider;
import com.example.training.common.wrappers.EnvironmentWrapper;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class EnvironmentPropertyProviderModule extends AbstractModule {

    @Override
    protected void configure() {
        PropertyProvider env = createEnvironmentPropertyProvider();
        bind(PropertyProvider.class).annotatedWith(Names.named("environmentPropertyProvider")).toInstance(env);
    }

    protected PropertyProvider createEnvironmentPropertyProvider() {
        return new EnvironmentPropertyProvider(new EnvironmentWrapper());
    }
}
