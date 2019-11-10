package com.example.training.graphy.linker;

import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.key.Key;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.HashMap;
import java.util.Map;

public class InMemoryLinker implements Linker {

    private final Map<Key<?>, Factory<?>> factories = new HashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <T> Factory<T> factoryFor(Key<T> key) {
        return (Factory<T>) factories.get(key);
    }

    @Override
    public <T> Factory<T> factoryFor(Class<T> clazz) {
        return factoryFor(Key.<T>builder().withClass(clazz).build());
    }

    @Override
    public <T> Factory<T> factoryFor(TypeReference<T> typeReference) {
        return factoryFor(Key.<T>builder().withTypeReference(typeReference).build());
    }

    @Override
    public <T> void install(Key<T> key, Factory<T> factory) {
        factories.put(key, factory);
    }

    @Override
    public <T> void install(Class<T> clazz, Factory<T> factory) {
        install(Key.<T>builder().withClass(clazz).build(), factory);
    }

    @Override
    public <T> void install(TypeReference<T> typeReference, Factory<T> factory) {
        install(Key.<T>builder().withTypeReference(typeReference).build(), factory);
    }
}
