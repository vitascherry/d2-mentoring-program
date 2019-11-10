package com.example.training.totodemo.graphy.aop;

import com.example.training.totodemo.annotation.ArraySize;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import static com.example.training.common.util.ProxyUtils.getMethod;

@RequiredArgsConstructor
public class ValidateArraySizeInvocationHandler<T> implements InvocationHandler {

    private final T target;

    @Override
    public Object invoke(Object proxy, Method proxyMethod, Object[] proxyArgs) throws Throwable {
        for (Parameter parameter : getMethod(target, proxyMethod).getParameters()) {
            final ArraySize size = parameter.getAnnotation(ArraySize.class);
            if (size != null && parameter.getType().isArray()) {
                for (Object arg : proxyArgs) {
                    if (arg instanceof Object[] && !inBounds((Object[]) arg, size.min(), size.max())) {
                        throw new IllegalArgumentException(size.message());
                    }
                }
            }
        }

        return proxyMethod.invoke(target, proxyArgs);
    }

    private static <T> boolean inBounds(T[] array, int min, int max) {
        return array.length >= min && array.length <= max;
    }
}
