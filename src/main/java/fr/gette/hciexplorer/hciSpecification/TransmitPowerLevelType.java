package fr.gette.hciexplorer.hciSpecification;

import java.util.HashMap;
import java.util.Map;

public enum TransmitPowerLevelType
{
    CURRENT(0x00),
    MAXIMUM(0x01);

    private static final Map<Integer, TransmitPowerLevelType> byValue = new HashMap<>();
    static {
        for (TransmitPowerLevelType e : TransmitPowerLevelType.values()) {
            if (byValue.put(e.getValue(), e) != null) {
                throw new IllegalArgumentException("duplicate value: " + e.getValue());
            }
        }
    }
    public static TransmitPowerLevelType get(int value) {
        return byValue.get(Integer.valueOf(value));
    }

    private final int value;

    TransmitPowerLevelType(int value)
    {
        this.value = value;
    }

    int getValue() {
        return value;
    }
}
