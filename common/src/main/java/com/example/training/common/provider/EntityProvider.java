package com.example.training.common.provider;

import java.util.List;

public interface EntityProvider<K, V> {

    List<V> getEntities();

    V getEntity(K key);
}
