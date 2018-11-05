package com.example.training.common.handler;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsoleHandler {

    private static final Scanner SCANNER = new Scanner(System.in);

    protected void printWithLineBreak(String message, Object... params) {
        print(true, message, params);
    }

    protected void print(String message, Object... params) {
        print(false, message, params);
    }

    private void print(boolean breakLine, String message, Object... params) {
        if (breakLine) {
            System.out.println(String.format(message, params));
        } else {
            System.out.print(String.format(message, params));
        }
    }

    protected void printLineBreak() {
        System.out.println();
    }

    protected String readNextLine(String message, Object... params) {
        print(message, params);
        return SCANNER.nextLine();
    }

    protected String readWithRetry(Pattern pattern, String message, String errorMessage) {
        while (true) {
            try {
                print(message);
                return read(pattern);
            } catch (NoSuchElementException e) {
                printWithLineBreak(errorMessage);
            } finally {
                flush();
            }
        }
    }

    private String read(Pattern pattern) {
        return SCANNER.next(pattern);
    }

    private void flush() {
        SCANNER.nextLine();
    }
}
