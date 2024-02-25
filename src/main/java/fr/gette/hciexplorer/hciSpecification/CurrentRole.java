package fr.gette.hciexplorer.hciSpecification;

import java.util.HashMap;
import java.util.Map;

public enum CurrentRole
{
    MASTER(0x00),
    SLAVE(0x01);

    private static final Map<Integer, CurrentRole> byValue = new HashMap<>();
    static {
        for (CurrentRole e : CurrentRole.values()) {
            if (byValue.put(e.getValue(), e) != null) {
                throw new IllegalArgumentException("duplicate value: " + e.getValue());
            }
        }
    }
    public static CurrentRole get(int value) {
        return byValue.get(Integer.valueOf(value));
    }

    private final int value;

    CurrentRole(int value)
    {
        this.value = value;
    }

    int getValue() {
        return value;
    }
}
