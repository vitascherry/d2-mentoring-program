package com.example.training.consolecommon.handler.impl;

import com.example.training.consolecommon.handler.Reader;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ReaderImpl implements Reader {

    private final Scanner scanner;

    public ReaderImpl(InputStream in) {
        this.scanner = new Scanner(in);
    }

    @Override
    public String read(Pattern pattern) {
        return pattern != null ? scanner.next(pattern) : read();
    }

    @Override
    public String read(String regex) {
        return regex != null ? scanner.next(regex) : read();
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void flush() {
        scanner.nextLine();
    }
}
