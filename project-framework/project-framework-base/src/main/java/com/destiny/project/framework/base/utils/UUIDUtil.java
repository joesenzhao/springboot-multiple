package com.destiny.project.framework.base.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public final class UUIDUtil {
    private static String[] CHARS = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public UUIDUtil() {
    }

    public static String getLowerCaseShortUuid() {
        return getShortUuid((String) null).toLowerCase();
    }

    public static String getLowerCaseShortUuid(String prefix) {
        return getShortUuid(prefix).toLowerCase();
    }

    public static String getUpperCaseShortUuid() {
        return getShortUuid((String) null).toUpperCase();
    }

    public static String getUpperCaseShortUuid(String prefix) {
        return getShortUuid(prefix).toUpperCase();
    }

    public static String getShortUuid(String prefix) {
        StringBuilder shortBuffer = new StringBuilder();
        if (StringUtils.isNotEmpty(prefix)) {
            shortBuffer.append(prefix);
        }

        String uuid = UUID.randomUUID().toString().replace("-", "");

        for (int i = 0; i < 8; ++i) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(CHARS[x % 62]);
        }

        return shortBuffer.toString();
    }

    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(getUpperCaseShortUuid());
    }
}

