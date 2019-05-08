package com.example.training.clientcommon.guice;

import com.example.training.common.guice.PropertyProviderModule;
import com.example.training.common.provider.FilePropertyProvider;
import com.example.training.common.provider.PropertyProvider;

import java.util.List;

import static com.example.training.clientcommon.constant.ClientConstants.CLIENT_COMMON_PROPERTY_FILE;
import static java.util.Collections.singletonList;

public class ClientFilePropertyProviderModule extends PropertyProviderModule {

    @Override
    protected List<PropertyProvider> createOverrideFilePropertyProviders() {
        return singletonList(new FilePropertyProvider(CLIENT_COMMON_PROPERTY_FILE));
    }
}
