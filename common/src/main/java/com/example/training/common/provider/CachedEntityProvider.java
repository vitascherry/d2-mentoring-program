package com.example.training.common.provider;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public abstract class CachedEntityProvider<K, V> implements EntityProvider<K, V> {

    protected Map<K, V> cache;

    @Override
    public List<V> getEntities() {
        initCacheIfNeeded();
        return new ArrayList<>(cache.values());
    }

    @Override
    public V getEntity(K key) {
        initCacheIfNeeded();
        return cache.get(key);
    }

    protected void initCacheIfNeeded() {
        if (cache == null) {
            cache = initCache();
        }
    }

    protected abstract Map<K, V> initCache();
}
