package com.example.training.common.graphy;

import com.example.training.graphy.factory.SingletonFactory;
import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public class ObjectMapperModule implements Module {

    @Override
    public void configure(Linker linker) {
        linker.install(ObjectMapper.class, SingletonFactory.of(this::createObjectMapper));
    }

    protected ObjectMapper createObjectMapper(Linker linker) {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .enable(INDENT_OUTPUT)
                .disable(FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(WRITE_DATES_AS_TIMESTAMPS);
    }
}
