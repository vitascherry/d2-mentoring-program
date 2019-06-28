package com.example.training.common.mapper;

import java.util.List;

import static java.util.stream.Collectors.toList;

public interface EntityMapper<I, O> {

    O map(I entity);

    I reverse(O entity);

    default List<O> map(List<I> entities) {
        return entities.stream().map(this::map).collect(toList());
    }

    default List<I> reverse(List<O> entities) {
        return entities.stream().map(this::reverse).collect(toList());
    }
}
