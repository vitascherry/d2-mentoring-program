package com.example.training.graphy.key;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.lang.reflect.Type;

@Getter
@EqualsAndHashCode
@Builder
@ToString
public final class Key {

    private final Type type;

    private final String name;

    private final Scope scope;
}
