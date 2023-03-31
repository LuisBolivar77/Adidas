package com.adidas.backend.prioritysaleservice.domain;

public class ThreadLocalData {

    private static ThreadLocal<String> threadLocalData = new ThreadLocal<>();

    public static void setValue(String value) {
        threadLocalData.set(value);
    }

    public static String getValue() {
        return threadLocalData.get();
    }
}
