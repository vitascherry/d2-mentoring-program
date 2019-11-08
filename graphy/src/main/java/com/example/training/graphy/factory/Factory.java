package com.example.training.graphy.factory;

import com.example.training.graphy.linker.Linker;

public interface Factory<T> {

    T get(Linker linker);
}
