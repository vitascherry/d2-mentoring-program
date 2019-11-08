package com.example.training.graphy.linker;

import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.key.Key;
import com.example.training.graphy.module.Module;
import com.fasterxml.jackson.core.type.TypeReference;

public interface Linker {

    void merge(Module one, Module second);

    <T> Factory<T> factoryFor(Key<T> key);

    <T> Factory<T> factoryFor(Class<T> clazz);

    <T> Factory<T> factoryFor(TypeReference<T> typeReference);

    <T> void install(Key<T> key, Factory<T> factory);

    <T> void install(Class<T> clazz, Factory<T> factory);

    <T> void install(TypeReference<T> typeReference, Factory<T> factory);
}
