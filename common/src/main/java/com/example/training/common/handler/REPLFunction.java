package com.example.training.common.handler;

import lombok.RequiredArgsConstructor;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class REPLFunction<R, T> {

    private final Printer printer;

    private final Reader reader;

    private boolean looped = false;

    private String message = "Enter value: ";

    private Pattern pattern = null;

    private Function<String, R> parser = (String s) -> (R) s;

    private Function<R, T> mapper = (R r) -> (T) r;

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

    public REPLFunction<R, T> withPattern(String pattern) {
        this.pattern = Pattern.compile(pattern);
        return this;
    }

    public REPLFunction<R, T> withPattern(Pattern pattern) {
        this.pattern = pattern;
        return this;
    }

    public REPLFunction<R, T> withParser(Function<String, R> parser) {
        this.parser = parser;
        return this;
    }

    public REPLFunction<R, T> withMapper(Function<R, T> mapper) {
        this.mapper = mapper;
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
                text = reader.read(pattern);
                value = parser.apply(text);

            } catch (Exception e) {
                printer.println(errorMessage);
                continue;

            }

            if (condition.test(value)) {
                break;
            }
            printer.printf(badMessage, text);
            printer.println();

        } while (looped);

        return mapper.apply(value);
    }
}
