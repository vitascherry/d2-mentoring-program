package com.example.training.graphy.key;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public final class Key<T> {

    private final Class<T> clazz;

    private final TypeReference<T> typeReference;

    private final String name;

    private Key(Builder<T> builder) {
        this.clazz = builder.clazz;
        this.typeReference = builder.typeReference;
        this.name = builder.name;
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static final class Builder<T> {

        private Class<T> clazz;

        private TypeReference<T> typeReference;

        private String name;

        public Builder<T> withClass(Class<T> clazz) {
            this.clazz = clazz;
            return this;
        }

        public Builder<T> withTypeReference(TypeReference<T> typeReference) {
            this.typeReference = typeReference;
            return this;
        }

        public Builder<T> withName(String name) {
            this.name = name;
            return this;
        }

        public Key<T> build() {
            return new Key<>(this);
        }
    }
}
