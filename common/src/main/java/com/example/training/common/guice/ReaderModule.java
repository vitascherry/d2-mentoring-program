package com.example.training.common.guice;

import com.example.training.common.handler.Reader;
import com.google.inject.AbstractModule;

public class ReaderModule extends AbstractModule {

    protected Reader reader;

    @Override
    protected void configure() {
        bindReader();
    }

    protected void bindReader() {
        reader = new Reader(System.in);

        bind(Reader.class).toInstance(reader);
    }
}
