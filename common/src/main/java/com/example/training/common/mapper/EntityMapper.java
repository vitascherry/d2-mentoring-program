package com.example.training.common.mapper;

import java.util.List;

import static java.util.stream.Collectors.toList;

@FunctionalInterface
public interface EntityMapper<I, O> {

    O map(I entity);

    default List<O> map(List<I> entities) {
        return entities.stream().map(this::map).collect(toList());
    }
}
