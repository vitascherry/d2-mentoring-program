package com.example.training.graphy.linker;

import com.example.training.graphy.exception.MissingFactoryException;
import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.key.Key;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.Closeable;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class InMemoryLinker implements Linker {

    private final Map<Key, Factory<?>> factories = new LinkedHashMap<>();
    private final Set<Closeable> provisions = new LinkedHashSet<>();

    @Override
    public <T> void bindProvision(@NonNull Closeable provision) {
        provisions.add(provision);
    }

    @Override
    public void closeAll() {
        provisions.forEach(InMemoryLinker::close);
    }

    @SneakyThrows(IOException.class)
    private static void close(Closeable closeable) {
        closeable.close();
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
