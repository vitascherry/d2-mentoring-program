package com.example.training.graphy.linker;

import com.example.training.graphy.CloseableProvision;
import com.example.training.graphy.exception.UnsatisfiedScopeException;
import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.key.Key;
import com.example.training.graphy.module.Module;

import java.lang.reflect.Type;

import static com.example.training.graphy.key.Scope.SINGLETON;

public interface Linker {

    <T> void bindProvision(Key key, CloseableProvision<T> provision) throws UnsatisfiedScopeException;

    default <T> void bindProvision(Class<T> clazz, CloseableProvision<T> provision) throws UnsatisfiedScopeException {
        bindProvision(Key.builder().type(clazz).scope(SINGLETON).build(), provision);
    }

    default <T> void bindProvision(Type type, CloseableProvision<T> provision) throws UnsatisfiedScopeException {
        bindProvision(Key.builder().type(type).scope(SINGLETON).build(), provision);
    }

    void closeAll();

    default void merge(Module one, Module second) {
        one.configure(this);
        // Override Factory<T> associated with same Key
        second.configure(this);
    }

    <T> Factory<T> factoryFor(Key key);

    default <T> Factory<T> factoryFor(Class<T> clazz) {
        return factoryFor(Key.builder().type(clazz).scope(SINGLETON).build());
    }

    default <T> Factory<T> factoryFor(Type type) {
        return factoryFor(Key.builder().type(type).scope(SINGLETON).build());
    }

    <T> void install(Key key, Factory<T> factory);

    default <T> void install(Class<T> clazz, Factory<T> factory) {
        install(Key.builder().type(clazz).scope(SINGLETON).build(), factory);
    }

    default <T> void install(Type type, Factory<T> factory) {
        install(Key.builder().type(type).scope(SINGLETON).build(), factory);
    }
}
