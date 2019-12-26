package com.example.training.graphy.linker;

import com.example.training.graphy.CloseableProvision;
import com.example.training.graphy.exception.MissingFactoryException;
import com.example.training.graphy.exception.UnsatisfiedScopeException;
import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.key.Key;
import com.example.training.graphy.key.Scope;

import java.util.LinkedHashMap;
import java.util.Map;

public class InMemoryLinker implements Linker {

    private final Map<Key, Factory<?>> factories = new LinkedHashMap<>();
    private final Map<Key, CloseableProvision<?>> provisions = new LinkedHashMap<>();

    @Override
    public <T> void bindProvision(Key key, CloseableProvision<T> provision) throws UnsatisfiedScopeException {
        if (key.getScope() != Scope.SINGLETON) {
            throw new UnsatisfiedScopeException(key.getScope());
        }
        provisions.put(key, provision);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void closeAll() {
        provisions.forEach((Key key, CloseableProvision provision) -> {
            Factory<?> factory = factoryFor(key);
            Object closeable = factory.get(this);
            provision.close(closeable);
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Factory<T> factoryFor(Key key) {
        return (Factory<T>) factories.getOrDefault(key, linker -> {
            throw new MissingFactoryException(key);
        });
    }

    @Override
    public <T> void install(Key key, Factory<T> factory) {
        factories.put(key, factory);
    }
}
