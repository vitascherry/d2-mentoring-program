package com.example.training.common.handler;

import lombok.extern.log4j.Log4j2;

import java.util.function.Function;
import java.util.function.Predicate;

@Log4j2
public class REPLFunction<R, T> extends ConsoleHandler {

    private boolean looped;

    private String message;

    private Function<String, R> parser;

    private final Function<String, R> defaultParser = (String s) -> (R) s;

    private Function<R, T> filter;

    private final Function<R, T> defaultFilter = (R r) -> (T) r;

    private Predicate<R> condition;

    private String errorMessage;

    public REPLFunction<R, T> withLoop() {
        this.looped = true;
        return this;
    }

    public REPLFunction<R, T> withMessage(String message) {
        this.message = message;
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

    public REPLFunction<R, T> withErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public T eval() {
        String text = readNextLine(message != null ? message : "Enter value: ");
        R value = null;

        do {
            try {
                value = parser != null ? parser.apply(text) : defaultParser.apply(text);
            } catch (Exception e) {
                log.error("Error occurred while evaluating REPL function. ErrorMessage: ", e);
                printWithLineBreak(errorMessage, text);
            }
            if (condition == null || condition.test(value)) {
                break;
            }
            printWithLineBreak(errorMessage, text);

        } while (looped);

        return filter != null ? filter.apply(value) : defaultFilter.apply(value);
    }
}
