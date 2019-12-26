package com.example.training.graphy.factory;

import com.example.training.graphy.linker.Linker;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SingletonFactory {

    public static <T> Factory<T> of(Function<Linker, T> factory) {
        final Map<Linker, T> lookup = new ConcurrentHashMap<>(1);
        return linker -> lookup.computeIfAbsent(linker, factory);
    }
}
