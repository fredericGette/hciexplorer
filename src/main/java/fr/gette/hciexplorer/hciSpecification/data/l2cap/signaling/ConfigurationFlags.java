package fr.gette.hciexplorer.hciSpecification.data.l2cap.signaling;

import java.util.HashMap;
import java.util.Map;

public enum ConfigurationFlags {
    NO_FLAG(0x0000),
    CONTINUATION_FLAG(0x0001);

    private static final Map<Integer, ConfigurationFlags> byValue = new HashMap<>();
    static {
        for (ConfigurationFlags e : ConfigurationFlags.values()) {
            if (byValue.put(e.getValue(), e) != null) {
                throw new IllegalArgumentException("duplicate value: " + e.getValue());
            }
        }
    }

    public static ConfigurationFlags get(Integer value) {
        return byValue.get(value);
    }

    private final int value;

    ConfigurationFlags(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
