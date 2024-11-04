package fr.gette.hciexplorer.hciSpecification.data.l2cap.signaling.configurationParameter;

import java.util.HashMap;
import java.util.Map;

public enum ConfigurationParameterType {
    MAXIMUM_TRANSMISSION_UNIT(0x01);

    private static final Map<Integer, ConfigurationParameterType> byValue = new HashMap<>();
    static {
        for (ConfigurationParameterType e : ConfigurationParameterType.values()) {
            if (byValue.put(e.getValue(), e) != null) {
                throw new IllegalArgumentException("duplicate value: " + e.getValue());
            }
        }
    }

    public static ConfigurationParameterType get(int value) {
        return byValue.get(Integer.valueOf(value));
    }

    private final int value;

    ConfigurationParameterType(int value)
    {
        this.value = value;
    }

    int getValue() {
        return value;
    }
}
