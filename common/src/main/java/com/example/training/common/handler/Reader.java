package com.example.training.common.handler;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Reader {

    private final Scanner scanner;

    public Reader(InputStream in) {
        this.scanner = new Scanner(in);
    }

    public String read(Pattern pattern) {
        return pattern != null ? scanner.next(pattern) : read();
    }

    public String read(String regex) {
        return regex != null ? scanner.next(regex) : read();
    }

    public String read() {
        return scanner.nextLine();
    }

    public void flush() {
        scanner.nextLine();
    }
}
