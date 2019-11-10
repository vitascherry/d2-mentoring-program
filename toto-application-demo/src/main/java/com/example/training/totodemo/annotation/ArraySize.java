package com.example.training.totodemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface ArraySize {

    int min() default 0;

    int max() default Integer.MAX_VALUE;

    String message() default "array size should be in specified bounds";
}
