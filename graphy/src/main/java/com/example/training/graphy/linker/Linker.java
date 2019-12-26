package com.example.training.graphy.linker;

import com.example.training.graphy.CloseableProvision;
import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.key.Key;
import com.example.training.graphy.module.Module;

import java.lang.reflect.Type;

public interface Linker {

    <T> void bindProvision(Key key, CloseableProvision<T> provision);

    default <T> void bindProvision(Class<T> clazz, CloseableProvision<T> provision) {
        bindProvision(Key.builder().type(clazz).build(), provision);
    }

    default <T> void bindProvision(Type type, CloseableProvision<T> provision) {
        bindProvision(Key.builder().type(type).build(), provision);
    }

    void closeAll();

    default void merge(Module one, Module second) {
        one.configure(this);
        // Override Factory<T> associated with same Key
        second.configure(this);
    }

    <T> Factory<T> factoryFor(Key key);

    default <T> Factory<T> factoryFor(Class<T> clazz) {
        return factoryFor(Key.builder().type(clazz).build());
    }

    default <T> Factory<T> factoryFor(Type type) {
        return factoryFor(Key.builder().type(type).build());
    }

    <T> void install(Key key, Factory<T> factory);

    default <T> void install(Class<T> clazz, Factory<T> factory) {
        install(Key.builder().type(clazz).build(), factory);
    }

    default <T> void install(Type type, Factory<T> factory) {
        install(Key.builder().type(type).build(), factory);
    }
}
