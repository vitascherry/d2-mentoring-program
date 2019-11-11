package com.example.training.graphy.linker;

import com.example.training.graphy.exception.MissingFactoryException;
import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.key.Key;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class InMemoryLinker implements Linker {

    private final Map<Key, Factory<?>> factories = new HashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <T> Factory<T> factoryFor(Key key) {
        return (Factory<T>) factories.getOrDefault(key, linker -> {
            throw new MissingFactoryException(key);
        });
    }

    @Override
    public <T> Factory<T> factoryFor(Class<T> clazz) {
        return factoryFor(Key.builder().type(clazz).build());
    }

    @Override
    public <T> Factory<T> factoryFor(Type type) {
        return factoryFor(Key.builder().type(type).build());
    }

    @Override
    public <T> void install(Key key, Factory<T> factory) {
        factories.put(key, factory);
    }

    @Override
    public <T> void install(Class<T> clazz, Factory<T> factory) {
        install(Key.builder().type(clazz).build(), factory);
    }

    @Override
    public <T> void install(Type type, Factory<T> factory) {
        install(Key.builder().type(type).build(), factory);
    }
}
