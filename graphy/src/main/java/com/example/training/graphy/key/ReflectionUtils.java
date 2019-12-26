package com.example.training.graphy.key;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.inject.Named;
import javax.inject.Singleton;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ReflectionUtils {

    public static Key getKey(Method method) {
        Named named = method.getAnnotation(Named.class);
        String name = named != null ? named.value() : null;
        Scope scope = method.isAnnotationPresent(Singleton.class) ? Scope.SINGLETON : Scope.PROTOTYPE;
        Type returnType = method.getGenericReturnType();
        return Key.builder().type(returnType).name(name).scope(scope).build();
    }
}
