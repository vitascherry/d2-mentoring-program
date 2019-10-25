package com.example.training.clientcommon.guice;

import com.example.training.clientcommon.util.RequestFactory;
import com.example.training.clientcommon.util.ResponseFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.apache.http.client.fluent.Request;

import static javax.ws.rs.HttpMethod.*;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static org.apache.http.entity.ContentType.TEXT_XML;

public class ClientAggregateModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new HttpClientModule());
    }

    @Singleton
    @Provides
    public RequestFactory requestFactoryProvider() {
        return RequestFactory.builder()
                .supports(HEAD, Request::Head)
                .supports(POST, Request::Post)
                .supports(GET, Request::Get)
                .supports(PUT, Request::Put)
                .supports(DELETE, Request::Delete).build();
    }

    @Singleton
    @Provides
    public ResponseFactory responseFactoryProvider(ObjectMapper jsonMapper, XmlMapper xmlMapper) {
        return ResponseFactory.builder()
                .mapper(APPLICATION_JSON.getMimeType(), jsonMapper)
                .mapper(TEXT_XML.getMimeType(), xmlMapper)
                .build();
    }
}
