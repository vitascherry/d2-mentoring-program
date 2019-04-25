package com.example.training.clientcommon.guice;

import com.example.training.common.guice.FilePropertyProviderModule;
import com.example.training.common.provider.CombinedPropertyProvider;
import com.example.training.common.provider.FilePropertyProvider;
import com.example.training.common.provider.PropertyProvider;

import static com.example.training.clientcommon.constant.ClientConstants.CLIENT_COMMON_PROPERTY_FILE;

public class ClientFilePropertyProviderModule extends FilePropertyProviderModule {

    @Override
    protected PropertyProvider createCommonFilePropertyProvider() {
        return CombinedPropertyProvider.builder()
                .provider(new FilePropertyProvider(CLIENT_COMMON_PROPERTY_FILE))
                .provider(super.createCommonFilePropertyProvider())
                .build();
    }
}
