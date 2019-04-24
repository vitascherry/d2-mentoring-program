package com.example.training.common.wrappers;

public class EnvironmentWrapper {

    public String getProperty(String key) {
        return System.getenv(key);
    }
}
