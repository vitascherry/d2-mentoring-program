package com.example.training.totodemo.graphy.interceptors;

import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Set;

import static javax.validation.Validation.buildDefaultValidatorFactory;

@RequiredArgsConstructor
public class ValidateReturnValueInvocationHandler<T> implements InvocationHandler {

    private final T object;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object returnValue = method.invoke(object, args);
        Set<ConstraintViolation<T>> violations = buildDefaultValidatorFactory()
                .getValidator()
                .forExecutables()
                .validateReturnValue(object, method, returnValue);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        return returnValue;
    }
}
