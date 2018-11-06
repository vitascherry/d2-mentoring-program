package com.example.training.common.handler;

import lombok.RequiredArgsConstructor;

import java.util.function.Function;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class REPLFunction<R, T> {

    private final Printer printer;

    private final Reader reader;

    private boolean looped = false;

    private String message = "Enter value: ";

    private Function<String, R> parser = (String s) -> (R) s;

    private Function<R, T> filter = (R r) -> (T) r;

    private Predicate<R> condition = (R r) -> true;

    private String errorMessage = "Invalid input!";

    private String badMessage = "Invalid value %s";

    public REPLFunction<R, T> withLoop() {
        this.looped = true;
        return this;
    }

    public REPLFunction<R, T> withMessage(String message, Object... args) {
        this.message = String.format(message, args);
        return this;
    }

    public REPLFunction<R, T> withParser(Function<String, R> parser) {
        this.parser = parser;
        return this;
    }

    public REPLFunction<R, T> withFilter(Function<R, T> filter) {
        this.filter = filter;
        return this;
    }

    public REPLFunction<R, T> withCondition(Predicate<R> condition) {
        this.condition = condition;
        return this;
    }

    public REPLFunction<R, T> withBadMessage(String badMessage) {
        this.badMessage = badMessage;
        return this;
    }

    public REPLFunction<R, T> withErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public T eval() {
        String text;
        R value = null;

        do {
            try {
                printer.print(message);
                text = reader.read();
                value = parser.apply(text);

            } catch (Exception e) {
                printer.println(errorMessage);
                continue;

            }

            if (condition.test(value)) {
                break;
            }
            printer.println(badMessage, text);

        } while (looped);

        return filter.apply(value);
    }
}
