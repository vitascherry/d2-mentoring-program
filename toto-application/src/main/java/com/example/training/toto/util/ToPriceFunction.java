package com.example.training.toto.util;

import com.example.training.toto.domain.Price;

@FunctionalInterface
public interface ToPriceFunction<T> {

    Price applyAsPrice(T value);
}
