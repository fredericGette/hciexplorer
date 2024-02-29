package fr.gette.hciexplorer.hciSpecification;

import java.util.HashMap;
import java.util.Map;

public enum AllowRoleSwitch
{
    NOT_ALLOWED(0x00),
    ALLOWED(0x01);

    private static final Map<Integer, AllowRoleSwitch> byValue = new HashMap<>();
    static {
        for (AllowRoleSwitch e : AllowRoleSwitch.values()) {
            if (byValue.put(e.getValue(), e) != null) {
                throw new IllegalArgumentException("duplicate value: " + e.getValue());
            }
        }
    }
    public static AllowRoleSwitch get(int value) {
        return byValue.get(Integer.valueOf(value));
    }

    private final int value;

    AllowRoleSwitch(int value)
    {
        this.value = value;
    }

    int getValue() {
        return value;
    }
}
