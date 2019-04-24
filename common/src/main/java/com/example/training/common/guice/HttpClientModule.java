package com.example.training.common.guice;

import com.example.training.common.provider.PropertyProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import static com.example.training.common.constant.CommonProperties.CONNECTION_TIME_TO_LIVE_MILLIS;
import static com.example.training.common.constant.DefaultConstants.CONNECTION_TIME_TO_LIVE_MILLIS_DEFAULT;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class HttpClientModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Singleton
    @Provides
    public HttpClient httpClientProvider(@Named("combinedPropertyProvider") PropertyProvider propertyProvider) {
        long connectionTimeToLive =
                propertyProvider.getLongProperty(CONNECTION_TIME_TO_LIVE_MILLIS, CONNECTION_TIME_TO_LIVE_MILLIS_DEFAULT);

        return HttpClientBuilder
                .create()
                .setConnectionTimeToLive(connectionTimeToLive, MILLISECONDS)
                .build();
    }
}
