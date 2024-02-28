package fr.gette.hciexplorer.hciSpecification;

import java.util.HashMap;
import java.util.Map;

public enum SimultaneousLeHost
{
    DISABLED(0x00),
    ENABLED(0x01);

    private static final Map<Integer, SimultaneousLeHost> byValue = new HashMap<>();
    static {
        for (SimultaneousLeHost e : SimultaneousLeHost.values()) {
            if (byValue.put(e.getValue(), e) != null) {
                throw new IllegalArgumentException("duplicate value: " + e.getValue());
            }
        }
    }
    public static SimultaneousLeHost get(int value) {
        return byValue.get(Integer.valueOf(value));
    }

    private final int value;

    SimultaneousLeHost(int value)
    {
        this.value = value;
    }

    int getValue() {
        return value;
    }
}
