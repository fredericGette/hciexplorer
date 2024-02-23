package fr.gette.hciexplorer.hciSpecification;

import java.util.HashMap;
import java.util.Map;

public enum LinkType
{
    SCO(0x00),
    ACL(0x01);

    private static final Map<Integer, LinkType> byValue = new HashMap<>();
    static {
        for (LinkType e : LinkType.values()) {
            if (byValue.put(e.getValue(), e) != null) {
                throw new IllegalArgumentException("duplicate value: " + e.getValue());
            }
        }
    }
    public static LinkType get(int value) {
        return byValue.get(Integer.valueOf(value));
    }

    private final int value;

    LinkType(int value)
    {
        this.value = value;
    }

    int getValue() {
        return value;
    }
}
