package com.example.training.graphy;

@FunctionalInterface
public interface CloseableProvision<T> {

    void close(T closeable);
}
