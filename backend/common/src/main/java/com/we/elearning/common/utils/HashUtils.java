package com.we.elearning.common.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.math.BigInteger;
import java.security.MessageDigest;

@UtilityClass
public class HashUtils {
    @SneakyThrows
    public String getHash(String input, int length) {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashBytes = md.digest(input.getBytes());
        return new BigInteger(1, hashBytes).toString(16).substring(0, length);
    }
}
