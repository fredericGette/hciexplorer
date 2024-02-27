package fr.gette.hciexplorer.hciSpecification;

import java.util.HashMap;
import java.util.Map;

public enum AuthenticationEnable {
    AUTHENTICATION_NOT_REQUIRED(0x00),
    AUTHENTICATION_REQUIRED_FOR_ALL_CONNECTIONS(0x01);

    private static final Map<Integer, AuthenticationEnable> byValue = new HashMap<>();
    static {
        for (AuthenticationEnable e : AuthenticationEnable.values()) {
            if (byValue.put(e.getValue(), e) != null) {
                throw new IllegalArgumentException("duplicate value: " + e.getValue());
            }
        }
    }

    public static AuthenticationEnable get(int value) {
        return byValue.get(Integer.valueOf(value));
    }
    private final int value;

    AuthenticationEnable(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
