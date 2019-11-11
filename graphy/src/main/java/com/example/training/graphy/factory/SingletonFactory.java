package com.example.training.graphy.factory;

import com.example.training.graphy.linker.Linker;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SingletonFactory {

    public static <T> Factory<T> of(Function<Linker, T> factory) {
        // TODO: Change implementation of caching value returned by factory to be effective in Servlet environment
        final Map<Linker, T> lookup = new HashMap<>();
        return linker -> lookup.computeIfAbsent(linker, factory);
    }
}
