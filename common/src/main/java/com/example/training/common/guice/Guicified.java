package com.example.training.common.guice;

import com.example.training.common.guice.annotation.WithModules;
import com.google.inject.Injector;
import com.google.inject.Module;

import java.util.LinkedHashSet;

import static com.google.inject.Guice.createInjector;

public interface Guicified {

    default Injector getInjector() {
        Class<?> currentClass = getClass();
        LinkedHashSet<Module> modules = new LinkedHashSet<>();
        while (currentClass != null) {
            WithModules withModules = currentClass.getAnnotation(WithModules.class);
            if (withModules != null) {
                for (Class<? extends Module> clazz : withModules.value()) {
                    try {
                        modules.add(clazz.newInstance());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            currentClass = currentClass.getSuperclass();
        }

        return createInjector(modules);
    }
}
