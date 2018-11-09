package com.example.training.passwordcracker.service;

import com.google.common.hash.Hasher;

import static com.google.common.hash.Hashing.md5;
import static java.nio.charset.StandardCharsets.UTF_8;

public class HashCalculator {

    public String hash(String toHash) {
        Hasher hasher = md5().newHasher();
        hasher.putString(toHash, UTF_8);
        return hasher.hash().toString();
    }
}
