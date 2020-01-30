package com.example.training.graphy;

import com.example.training.graphy.annotation.WithModules;
import com.example.training.graphy.module.Module;
import lombok.SneakyThrows;

import java.util.LinkedHashSet;
import java.util.Set;

import static com.example.training.graphy.Graphy.graphy;

public interface Graphied {

    @SneakyThrows({InstantiationException.class, IllegalAccessException.class})
    default ObjectGraph getObjectGraph() {
        Class<?> currentClass = getClass();
        Set<Module> modules = new LinkedHashSet<>();
        while (currentClass != null) {
            WithModules withModules = currentClass.getAnnotation(WithModules.class);
            if (withModules != null) {
                for (Class<? extends Module> clazz : withModules.value()) {
                    modules.add(clazz.newInstance());
                }
            }
            currentClass = currentClass.getSuperclass();
        }

        return graphy(modules);
    }
}
