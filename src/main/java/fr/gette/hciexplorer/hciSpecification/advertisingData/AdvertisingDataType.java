package fr.gette.hciexplorer.hciSpecification.advertisingData;

import java.util.HashMap;
import java.util.Map;

public enum AdvertisingDataType {
    FLAGS(0x01),
    COMPLETE_LIST_OF_16_BIT_UUIDS(0x03),
    COMPLETE_LOCAL_NAME(0x09),
    APPEARANCE(0x19);

    private static final Map<Integer, AdvertisingDataType> byValue = new HashMap<>();
    static {
        for (AdvertisingDataType e : AdvertisingDataType.values()) {
            if (byValue.put(e.getValue(), e) != null) {
                throw new IllegalArgumentException("duplicate value: " + e.getValue());
            }
        }
    }
    public static AdvertisingDataType get(int value) {
        return byValue.get(Integer.valueOf(value));
    }

    private final int value;

    AdvertisingDataType(int value)
    {
        this.value = value;
    }

    int getValue() {
        return value;
    }
}