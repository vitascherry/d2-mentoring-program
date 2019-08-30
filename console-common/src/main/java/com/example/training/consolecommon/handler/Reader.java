package com.example.training.consolecommon.handler;

import java.util.regex.Pattern;

public interface Reader {

    String read(Pattern pattern);

    String read(String regex);

    String read();

    void flush();
}
