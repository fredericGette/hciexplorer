package fr.gette.hciexplorer.hciSpecification.extendedInquiryResponse;

import java.util.HashMap;
import java.util.Map;

public enum EirDataType {
    COMPLETE_LOCAL_NAME(0x09),
    TX_POWER_LEVEL(0x0A);

    private static final Map<Integer, EirDataType> byValue = new HashMap<>();
    static {
        for (EirDataType e : EirDataType.values()) {
            if (byValue.put(e.getValue(), e) != null) {
                throw new IllegalArgumentException("duplicate value: " + e.getValue());
            }
        }
    }
    public static EirDataType get(int value) {
        return byValue.get(Integer.valueOf(value));
    }

    private final int value;

    EirDataType(int value)
    {
        this.value = value;
    }

    int getValue() {
        return value;
    }
}
