package fr.gette.hciexplorer.hciSpecification;

import java.util.HashMap;
import java.util.Map;

public enum InquiryScanType
{
    STANDARD_SCAN(0x00),
    INTERLACED_SCAN(0x01);

    private static final Map<Integer, InquiryScanType> byValue = new HashMap<>();
    static {
        for (InquiryScanType e : InquiryScanType.values()) {
            if (byValue.put(e.getValue(), e) != null) {
                throw new IllegalArgumentException("duplicate value: " + e.getValue());
            }
        }
    }
    public static InquiryScanType get(int value) {
        return byValue.get(Integer.valueOf(value));
    }

    private final int value;

    InquiryScanType(int value)
    {
        this.value = value;
    }

    int getValue() {
        return value;
    }
}
