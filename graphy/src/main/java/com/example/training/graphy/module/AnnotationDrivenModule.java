package com.example.training.graphy.module;

import com.example.training.graphy.annotation.Import;
import com.example.training.graphy.annotation.Provides;
import com.example.training.graphy.factory.SingletonFactory;
import com.example.training.graphy.linker.Linker;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import javax.inject.Singleton;
import java.lang.reflect.Method;

import static com.example.training.graphy.key.ReflectionUtils.getKey;

public abstract class AnnotationDrivenModule implements Module {

    @Override
    @SneakyThrows
    public void configure(Linker linker) {
        Import imports = getClass().getAnnotation(Import.class);
        if (imports != null) {
            for (Class<? extends Module> clazz : imports.value()) {
                clazz.newInstance().configure(linker);
            }
        }

        for (Method method : getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Provides.class)) {
                if (method.isAnnotationPresent(Singleton.class)) {
                    linker.install(getKey(method), SingletonFactory.of(new InvokeFactory<>(method)::invoke));
                } else {
                    linker.install(getKey(method), new InvokeFactory<>(method)::invoke);
                }
            }
        }
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    private final class InvokeFactory<T> {

        private final Method method;

        @SuppressWarnings("unchecked")
        @SneakyThrows
        private T invoke(Linker linker) {
            if (method.getParameterCount() == 0) {
                return (T) method.invoke(AnnotationDrivenModule.this);
            }
            return (T) method.invoke(AnnotationDrivenModule.this, linker);
        }
    }
}
