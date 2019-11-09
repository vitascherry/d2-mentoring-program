package com.example.training.totodemo.graphy.aop;

import com.example.training.toto.domain.Outcome;
import com.example.training.totodemo.annotation.ValidateOutcomes;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

@RequiredArgsConstructor
public class ValidateOutcomesInvocationHandler<T> implements InvocationHandler {

    private final T target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getDeclaredAnnotation(ValidateOutcomes.class) != null && Arrays.stream(args)
                .filter(arg -> arg instanceof Outcome[])
                .map(arg -> (Outcome[]) arg)
                .noneMatch(outcomes -> outcomes.length == 14)) {
            throw new IllegalArgumentException("Games count in outcomes should be 14");
        }

        return method.invoke(target, args);
    }
}
