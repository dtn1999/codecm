package com.we.elearning.common.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RandomUtils {
    public int getNumber(int low, int high) {
        return (int) (Math.random() * (high - low) + low);
    }
}
