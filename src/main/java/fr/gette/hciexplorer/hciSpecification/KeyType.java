package fr.gette.hciexplorer.hciSpecification;

import java.util.HashMap;
import java.util.Map;

public enum KeyType
{
    COMBINATION(0x00),
    LOCAL_UNIT(0x01),
    REMOTE_UNIT(0x02),
    DEBUG_COMBINATION(0x03),
    UNAUTHENTICATED_COMBINATION(0x04),
    AUTHENTICATED_COMBINATION(0x05),
    CHANGED_COMBINATION(0x06);


    private static final Map<Integer, KeyType> byValue = new HashMap<>();
    static {
        for (KeyType e : KeyType.values()) {
            if (byValue.put(e.getValue(), e) != null) {
                throw new IllegalArgumentException("duplicate value: " + e.getValue());
            }
        }
    }
    public static KeyType get(int value) {
        return byValue.get(Integer.valueOf(value));
    }

    private final int value;

    KeyType(int value)
    {
        this.value = value;
    }

    int getValue() {
        return value;
    }
}
