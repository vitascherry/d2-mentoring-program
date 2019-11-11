package com.example.training.graphy.linker;

import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.key.Key;
import com.example.training.graphy.module.Module;

import java.lang.reflect.Type;

public interface Linker {

    default void merge(Module one, Module second) {
        one.configure(this);
        // Override Factory<T> associated with same Key
        second.configure(this);
    }

    <T> Factory<T> factoryFor(Key key);

    <T> Factory<T> factoryFor(Class<T> clazz);

    <T> Factory<T> factoryFor(Type type);

    <T> void install(Key key, Factory<T> factory);

    <T> void install(Class<T> clazz, Factory<T> factory);

    <T> void install(Type type, Factory<T> factory);
}
