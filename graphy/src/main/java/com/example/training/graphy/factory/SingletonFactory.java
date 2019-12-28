package com.example.training.graphy.factory;

import com.example.training.graphy.linker.Linker;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class SingletonFactory<T> implements Factory<T> {

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

    public static <T> Factory<T> of(Factory<T> delegate) {
        log.debug("Memoizing {}", delegate);
        return new SingletonFactory<>(delegate);
    }
}
