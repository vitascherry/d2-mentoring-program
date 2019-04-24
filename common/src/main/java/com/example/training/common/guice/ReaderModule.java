package com.example.training.common.guice;

import com.example.training.common.handler.Reader;
import com.google.inject.AbstractModule;

public class ReaderModule extends AbstractModule {

    @Override
    protected void configure() {
        Reader reader = createReader();
        bind(Reader.class).toInstance(reader);
    }

    protected Reader createReader() {
        return new Reader(System.in);
    }
}
