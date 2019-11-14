package com.example.training.graphy.proxy;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.lang.reflect.InvocationHandler;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Proxy {

    @SuppressWarnings("unchecked")
    public static <T> T of(InvocationHandler handler, @NonNull Class<? extends T> clazz) {
        return (T) java.lang.reflect.Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), handler);
    }
}
