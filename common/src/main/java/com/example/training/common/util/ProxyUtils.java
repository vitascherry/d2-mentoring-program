package com.example.training.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.lang.reflect.Method;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProxyUtils {

    public static <T> Method getMethod(@NonNull T target, Method proxyMethod) {
        for (Method method : target.getClass().getMethods()) {
            if (method.getName().equals(proxyMethod.getName())) {
                return method;
            }
        }
        throw new IllegalStateException(String.format("Not found '%s' method in target object", proxyMethod.getName()));
    }
}
