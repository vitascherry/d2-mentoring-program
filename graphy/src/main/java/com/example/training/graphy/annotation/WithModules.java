package com.example.training.graphy.annotation;

import com.example.training.graphy.module.Module;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface WithModules {

    Class<? extends Module>[] value();
}
