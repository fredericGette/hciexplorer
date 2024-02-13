package fr.gette.hciexplorer.hciSpecification;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public enum HciPacketType {
    Command(0x01),
    AclData(0x02),
    Event(0x04);

    private static final Map<Integer, HciPacketType> byValue = new HashMap<>();
    static {
        for (HciPacketType e : HciPacketType.values()) {
            if (byValue.put(e.getValue(), e) != null) {
                throw new IllegalArgumentException("duplicate value: " + e.getValue());
            }
        }
    }
    public static HciPacketType get(int value) {
        return byValue.get(Integer.valueOf(value));
    }

    public static HciPacketType get(long value) {
        if (value > Integer.MAX_VALUE)
        {
            throw new IllegalArgumentException(value + " > Integer.MAX_VALUE");
        }
        return get((int)value);
    }

    private final int value;

    HciPacketType(int value)
    {
        this.value = value;
    }

    int getValue() {
        return value;
    }
}
