package com.we.elearning.workspacemanager.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.math.BigInteger;
import java.security.MessageDigest;

@UtilityClass
public class RandomUtils {
    public int getNumber(int low, int high) {
        return (int) (Math.random() * (high - low) + low);
    }


}
