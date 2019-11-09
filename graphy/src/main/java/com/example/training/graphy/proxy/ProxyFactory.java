package com.example.training.graphy.proxy;

import com.example.training.graphy.factory.Factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public abstract class ProxyFactory<T> implements Factory<T> {

    @SuppressWarnings("unchecked")
    public static <T> Factory<T> decorate(InvocationHandler handler, ClassLoader classLoader, Class<? super T>... interfaceClasses) {
        return linker -> (T) Proxy.newProxyInstance(classLoader, interfaceClasses, handler);
    }
}
