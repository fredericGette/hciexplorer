package fr.gette.hciexplorer.hciSpecification;

import java.util.HashMap;
import java.util.Map;

public enum EncryptionEnabled
{
    DISABLED(0x00),
    ENABLED(0x01);

    private static final Map<Integer, EncryptionEnabled> byValue = new HashMap<>();
    static {
        for (EncryptionEnabled e : EncryptionEnabled.values()) {
            if (byValue.put(e.getValue(), e) != null) {
                throw new IllegalArgumentException("duplicate value: " + e.getValue());
            }
        }
    }
    public static EncryptionEnabled get(int value) {
        return byValue.get(Integer.valueOf(value));
    }

    private final int value;

    EncryptionEnabled(int value)
    {
        this.value = value;
    }

    int getValue() {
        return value;
    }
}
