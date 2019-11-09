package com.example.training.graphy;

import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.key.Key;
import com.example.training.graphy.linker.Linker;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ObjectGraph {

    private final Linker linker;

    public <T> T getInstance(Key<T> key) {
        Factory<T> factory = linker.factoryFor(key);
        return factory.get(linker);
    }

    public <T> T getInstance(TypeReference<T> typeReference) {
        return getInstance(Key.<T>builder().withTypeReference(typeReference).build());
    }

    public <T> T getInstance(Class<T> clazz) {
        return getInstance(Key.<T>builder().withClass(clazz).build());
    }
}
