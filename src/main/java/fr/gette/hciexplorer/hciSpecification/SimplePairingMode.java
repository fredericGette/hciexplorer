package fr.gette.hciexplorer.hciSpecification;

import java.util.HashMap;
import java.util.Map;

public enum SimplePairingMode {
    DISABLED(0x00),
    ENABLED(0x01);

    private static final Map<Integer, SimplePairingMode> byValue = new HashMap<>();
    static {
        for (SimplePairingMode e : SimplePairingMode.values()) {
            if (byValue.put(e.getValue(), e) != null) {
                throw new IllegalArgumentException("duplicate value: " + e.getValue());
            }
        }
    }

    public static SimplePairingMode get(int value) {
        return byValue.get(Integer.valueOf(value));
    }
    private final int value;

    SimplePairingMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
