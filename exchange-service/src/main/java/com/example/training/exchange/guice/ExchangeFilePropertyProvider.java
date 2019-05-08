package com.example.training.exchange.guice;

import com.example.training.clientcommon.guice.ClientFilePropertyProviderModule;
import com.example.training.common.provider.FilePropertyProvider;
import com.example.training.common.provider.PropertyProvider;

import java.util.ArrayList;
import java.util.List;

import static com.example.training.exchange.constant.ExchangeConstants.EXCHANGE_OVERRIDE_PROPERTY_FILE;

public class ExchangeFilePropertyProvider extends ClientFilePropertyProviderModule {

    @Override
    protected List<PropertyProvider> createOverrideFilePropertyProviders() {
        List<PropertyProvider> providers = new ArrayList<>();
        providers.add(new FilePropertyProvider(EXCHANGE_OVERRIDE_PROPERTY_FILE));
        providers.addAll(super.createOverrideFilePropertyProviders());
        return providers;
    }
}
