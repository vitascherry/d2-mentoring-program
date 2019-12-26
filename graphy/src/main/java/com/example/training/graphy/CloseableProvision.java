package com.example.training.graphy;

public interface CloseableProvision<T> {

    void close(T closeable);
}
