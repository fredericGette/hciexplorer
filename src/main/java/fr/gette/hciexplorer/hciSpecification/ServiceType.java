package fr.gette.hciexplorer.hciSpecification;

import java.util.HashMap;
import java.util.Map;

public enum ServiceType
{
    NO_TRAFFIC_AVAILABLE(0x00),
    BEST_EFFORT_AVAILABLE(0x01),
    GUARANTEED_AVAILABLE(0x02);

    private static final Map<Integer, ServiceType> byValue = new HashMap<>();
    static {
        for (ServiceType e : ServiceType.values()) {
            if (byValue.put(e.getValue(), e) != null) {
                throw new IllegalArgumentException("duplicate value: " + e.getValue());
            }
        }
    }
    public static ServiceType get(int value) {
        return byValue.get(Integer.valueOf(value));
    }

    private final int value;

    ServiceType(int value)
    {
        this.value = value;
    }

    int getValue() {
        return value;
    }
}
