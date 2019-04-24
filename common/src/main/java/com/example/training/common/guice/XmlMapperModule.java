package com.example.training.common.guice;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.AbstractModule;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public class XmlMapperModule extends AbstractModule {

    @Override
    protected void configure() {
        XmlMapper xmlMapper = createXmlMapper();
        bind(XmlMapper.class).toInstance(xmlMapper);
    }

    protected XmlMapper createXmlMapper() {
        return (XmlMapper) new XmlMapper()
                .registerModule(new JavaTimeModule())
                .disable(FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(WRITE_DATES_AS_TIMESTAMPS);
    }
}
