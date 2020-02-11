package com.example.training.graphy.module;

import com.example.training.graphy.annotation.Import;
import com.example.training.graphy.annotation.Provides;
import com.example.training.graphy.factory.SingletonFactory;
import com.example.training.graphy.linker.Linker;
import lombok.SneakyThrows;

import javax.inject.Singleton;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.example.training.graphy.key.ReflectionUtils.getKey;

public abstract class AnnotationDrivenModule implements Module {

    @Override
    @SneakyThrows({InstantiationException.class, IllegalAccessException.class})
    public final void configure(Linker linker) {
        Class<?> currentClass = getClass();
        Set<Module> importedModules = new LinkedHashSet<>();
        while (currentClass != null) {
            Import imports = getClass().getAnnotation(Import.class);
            if (imports != null) {
                for (Class<? extends Module> clazz : imports.value()) {
                    importedModules.add(clazz.newInstance());
                }
            }
            for (Method method : getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(Provides.class)) {
                    if (method.isAnnotationPresent(Singleton.class)) {
                        linker.install(getKey(method), SingletonFactory.of(linker1 -> invoke(linker1, method)));
                    } else {
                        linker.install(getKey(method), linker1 -> invoke(linker1, method));
                    }
                }
            }
            currentClass = currentClass.getSuperclass();
        }
        importedModules.forEach(module -> module.configure(linker));
    }

    @SuppressWarnings("unchecked")
    @SneakyThrows
    private <T> T invoke(Linker linker, Method method) {
        if (method.getParameterCount() == 0) {
            return (T) method.invoke(this);
        }
        return (T) method.invoke(this, linker);
    }
}
