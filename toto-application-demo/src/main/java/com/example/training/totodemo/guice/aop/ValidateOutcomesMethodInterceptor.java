package com.example.training.totodemo.guice.aop;

import com.example.training.toto.domain.Outcome;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.Arrays;

public class ValidateOutcomesMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (Arrays.stream(invocation.getArguments())
                .filter(arg -> arg instanceof Outcome[])
                .map(arg -> (Outcome[]) arg)
                .noneMatch(outcomes -> outcomes.length == 14)) {
            throw new IllegalArgumentException("Games count in outcomes should be 14");
        }

        return invocation.proceed();
    }
}
