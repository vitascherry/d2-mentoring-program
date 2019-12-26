package com.example.training.graphy.factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SingletonFactory {

    public static <T> Factory<T> of(Factory<T> delegate) {
        log.info("Memoizing {}", delegate);
        return new MemoizingFactory<>(delegate);
    }
}
