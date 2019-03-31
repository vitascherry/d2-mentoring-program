package com.example.training.common.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import static com.example.training.common.constant.CommonConstants.CONNECTION_TIMEOUT_MILLIS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class HttpClientModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Singleton
    @Provides
    public HttpClient httpClientProvider() {
        return HttpClientBuilder
                .create()
                .setConnectionTimeToLive(CONNECTION_TIMEOUT_MILLIS, MILLISECONDS)
                .build();
    }
}
