package com.example.training.common.handler;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Reader {

    private final Scanner scanner;

    public Reader(InputStream in) {
        this.scanner = new Scanner(in);
    }

    public String read() {
        return scanner.nextLine();
    }

    public String read(Pattern pattern) {
        return scanner.next(pattern);
    }

    public String read(String regex) {
        return scanner.next(regex);
    }

    public void flush() {
        scanner.nextLine();
    }
}
