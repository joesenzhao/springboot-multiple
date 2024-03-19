package com.destiny.project.framework.base.utils;

public class StringUtil {

    public static boolean isBlank(Object val) {
        return val == null || val.toString().isEmpty();
    }

    public static boolean isNotBlank(String str) {
        int length;
        if (str != null && (length = str.length()) != 0) {
            for (int i = 0; i < length; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return true;
                }
            }
        }
        return false;
    }
}
