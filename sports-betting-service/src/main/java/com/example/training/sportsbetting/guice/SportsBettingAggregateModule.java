package com.example.training.sportsbetting.guice;

import com.google.inject.AbstractModule;
import com.google.inject.util.Modules;

public class SportsBettingAggregateModule extends AbstractModule {

    @Override
    protected void configure() {
        // This allows to migrate from mock data to real data gradually, without changing the aggregation module
        install(Modules.override(new SportsBettingMockModule()).with(new SportsBettingModule()));
    }
}
