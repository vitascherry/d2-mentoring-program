package com.example.training.passwordcracker.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

import static com.example.training.passwordcracker.constant.HashCalculatorConstants.PASSWORD_CALCULATION_TIMEOUT_MIN;
import static java.util.concurrent.TimeUnit.MINUTES;

@AllArgsConstructor
public class PasswordCracker {

    private final HashCalculator hashCalculator;

    public String crack(final @NonNull String hash) throws InterruptedException, TimeoutException, ExecutionException {
        ExecutorService executor = Executors.newWorkStealingPool();
        Future<String> passwordHolder = executor.submit(() -> {
            String password = "";
            if (hash.equals(hashCalculator.hash(password))) {
                return password;
            }

            throw new IllegalStateException("Internal server error");
        });

        return passwordHolder.get(PASSWORD_CALCULATION_TIMEOUT_MIN, MINUTES);
    }
}
