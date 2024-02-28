package fr.gette.hciexplorer.hciSpecification;

import java.util.HashMap;
import java.util.Map;

public enum LeSupportedHost
{
    DISABLED(0x00),
    ENABLED(0x01);

    private static final Map<Integer, LeSupportedHost> byValue = new HashMap<>();
    static {
        for (LeSupportedHost e : LeSupportedHost.values()) {
            if (byValue.put(e.getValue(), e) != null) {
                throw new IllegalArgumentException("duplicate value: " + e.getValue());
            }
        }
    }
    public static LeSupportedHost get(int value) {
        return byValue.get(Integer.valueOf(value));
    }

    private final int value;

    LeSupportedHost(int value)
    {
        this.value = value;
    }

    int getValue() {
        return value;
    }
}
