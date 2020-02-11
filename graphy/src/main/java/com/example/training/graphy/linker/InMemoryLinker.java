package com.example.training.graphy.linker;

import com.example.training.graphy.exception.MissingFactoryException;
import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.key.Key;
import com.example.training.graphy.provision.Closeable;
import lombok.extern.log4j.Log4j2;

import java.util.LinkedHashMap;
import java.util.Map;

@Log4j2
public class InMemoryLinker implements Linker {

    private final Map<Key, Factory<?>> factories = new LinkedHashMap<>();
    private final Map<Key, Closeable> provisions = new LinkedHashMap<>();

    @Override
    public <T> void bindProvision(Key key, Closeable provision) {
        provisions.put(key, provision);
    }

    @Override
    public void closeAll() {
        provisions.forEach(InMemoryLinker::close);
    }

    private static void close(Key key, Closeable closeable) {
        log.debug("Closing resources of {}", key);
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
