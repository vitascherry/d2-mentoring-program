package com.example.training.graphy.factory;

import com.example.training.graphy.linker.Linker;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemoizingFactory<T> implements Factory<T> {

    private final Factory<T> delegate;
    private transient T value;

    @Override
    public T get(Linker linker) {
        if (value == null) {
            synchronized (this) {
                if (value == null) {
                    return value = delegate.get(linker);
                }
            }
        }
        return value;
    }
}
