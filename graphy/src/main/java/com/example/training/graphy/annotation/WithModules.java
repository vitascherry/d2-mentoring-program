package com.example.training.graphy.annotation;

import com.example.training.graphy.module.Module;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WithModules {

    Class<? extends Module>[] value();
}
