package com.example.training.common.graphy;

import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public class XmlMapperModule implements Module {

    @Override
    public void configure(Linker linker) {
        linker.install(XmlMapper.class, linker1 -> (XmlMapper) new XmlMapper()
                .registerModule(new JavaTimeModule())
                .disable(FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(WRITE_DATES_AS_TIMESTAMPS));
    }
}
