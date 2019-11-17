package com.example.training.toto.graphy.interceptors;

import lombok.AllArgsConstructor;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@AllArgsConstructor
public class EntityManagerInvocationHandler<T> implements InvocationHandler {

    private final EntityManagerHelper entityManagerHelper;
    private final T object;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (object.getClass().isAnnotationPresent(Transactional.class) || method.isAnnotationPresent(Transactional.class)) {
            entityManagerHelper.beginTransaction();
            try {
                Object returnValue = method.invoke(object, args);
                entityManagerHelper.commitTransaction();
                return returnValue;
            } catch (Throwable e) {
                entityManagerHelper.rollbackTransaction();
                throw e.getCause();
            } finally {
                entityManagerHelper.closeEntityManager();
            }
        }
        return method.invoke(object, args);
    }
}
