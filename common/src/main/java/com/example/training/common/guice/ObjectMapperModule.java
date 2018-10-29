package com.example.training.common.guice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.AbstractModule;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public class ObjectMapperModule extends AbstractModule {

    protected ObjectMapper objectMapper;

    @Override
    protected void configure() {
        bindObjectMapper();
    }

    protected void bindObjectMapper() {
        objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .enable(INDENT_OUTPUT)
                .disable(FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(WRITE_DATES_AS_TIMESTAMPS);

        bind(ObjectMapper.class).toInstance(objectMapper);
    }
}
