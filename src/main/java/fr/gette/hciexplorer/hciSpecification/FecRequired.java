package fr.gette.hciexplorer.hciSpecification;

import java.util.HashMap;
import java.util.Map;

public enum FecRequired {
    FEC_NOT_REQUIRED(0x00),
    FEC_REQUIRED(0x01);

    private static final Map<Integer, FecRequired> byValue = new HashMap<>();
    static {
        for (FecRequired e : FecRequired.values()) {
            if (byValue.put(e.getValue(), e) != null) {
                throw new IllegalArgumentException("duplicate value: " + e.getValue());
            }
        }
    }

    public static FecRequired get(int value) {
        return byValue.get(Integer.valueOf(value));
    }
    private final int value;

    FecRequired(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
