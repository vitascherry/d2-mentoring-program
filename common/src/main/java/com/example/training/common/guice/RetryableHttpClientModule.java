package com.example.training.common.guice;

import com.example.training.common.provider.PropertyProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.RetryableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import static com.example.training.common.constant.CommonProperties.CONNECTION_TIMEOUT_MILLIS;
import static com.example.training.common.constant.CommonProperties.CONNECTION_TIME_TO_LIVE_MILLIS;
import static com.example.training.common.constant.CommonProperties.MAX_WAIT_TIMEOUT_MILLIS;
import static com.example.training.common.constant.CommonProperties.RETRY_LIMIT;
import static com.example.training.common.constant.CommonProperties.SOCKET_TIMEOUT_MILLIS;
import static com.example.training.common.constant.DefaultConstants.CONNECTION_TIMEOUT_MILLIS_DEFAULT;
import static com.example.training.common.constant.DefaultConstants.CONNECTION_TIME_TO_LIVE_MILLIS_DEFAULT;
import static com.example.training.common.constant.DefaultConstants.MAX_WAIT_TIMEOUT_MILLIS_DEFAULT;
import static com.example.training.common.constant.DefaultConstants.RETRY_LIMIT_DEFAULT;
import static com.example.training.common.constant.DefaultConstants.SOCKET_TIMEOUT_MILLIS_DEFAULT;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class RetryableHttpClientModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Singleton
    @Provides
    public HttpClient retryableHttpClientProvider(@Named("combinedPropertyProvider") PropertyProvider propertyProvider) {
        int connectionTimeToLive =
                propertyProvider.getIntProperty(CONNECTION_TIME_TO_LIVE_MILLIS, CONNECTION_TIME_TO_LIVE_MILLIS_DEFAULT);
        int connectionTimeout =
                propertyProvider.getIntProperty(CONNECTION_TIMEOUT_MILLIS, CONNECTION_TIMEOUT_MILLIS_DEFAULT);
        int socketTimeout = propertyProvider.getIntProperty(SOCKET_TIMEOUT_MILLIS, SOCKET_TIMEOUT_MILLIS_DEFAULT);
        int maxWaitTimeout = propertyProvider.getIntProperty(MAX_WAIT_TIMEOUT_MILLIS, MAX_WAIT_TIMEOUT_MILLIS_DEFAULT);
        int retryLimit = propertyProvider.getIntProperty(RETRY_LIMIT, RETRY_LIMIT_DEFAULT);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectionTimeout)
                .setConnectionRequestTimeout(connectionTimeout)
                .setSocketTimeout(socketTimeout)
                .build();

        return new RetryableHttpClient(
                HttpClientBuilder.create()
                        .setConnectionTimeToLive(connectionTimeToLive, MILLISECONDS)
                        .setDefaultRequestConfig(requestConfig)
                        .build(),
                maxWaitTimeout,
                retryLimit
        );
    }
}
