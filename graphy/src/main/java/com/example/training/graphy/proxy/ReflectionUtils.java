package com.example.training.graphy.proxy;

import com.example.training.graphy.key.Key;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.inject.Named;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ReflectionUtils {

    public static Method getMethod(@NonNull Object target, Method proxyMethod) {
        for (Method method : target.getClass().getMethods()) {
            if (method.getName().equals(proxyMethod.getName())) {
                return method;
            }
        }
        throw new IllegalStateException(String.format("Not found '%s' method in target object", proxyMethod.getName()));
    }

    public static Key getKey(Method method) {
        Named named = method.getAnnotation(Named.class);
        String name = named != null ? named.value() : null;
        Type returnType = getReturnType(method);
        return Key.builder().type(returnType).name(name).build();
    }

    private static Type getReturnType(Method method) {
        return method.getGenericReturnType();
    }
}
