package com.example.training.graphy;

import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.key.Key;
import com.example.training.graphy.linker.Linker;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ObjectGraph {

    private final Linker linker;

    public <T> T getInstance(Key key) {
        Factory<T> factory = linker.factoryFor(key);
        return factory.get(linker);
    }

    public <T> T getInstance(Class<T> clazz) {
        return getInstance(Key.builder().type(clazz).build());
    }
}
