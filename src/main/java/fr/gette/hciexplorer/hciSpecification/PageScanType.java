package fr.gette.hciexplorer.hciSpecification;

import java.util.HashMap;
import java.util.Map;

public enum PageScanType
{
    STANDARD_SCAN(0x00),
    INTERLACED_SCAN(0x01);

    private static final Map<Integer, PageScanType> byValue = new HashMap<>();
    static {
        for (PageScanType e : PageScanType.values()) {
            if (byValue.put(e.getValue(), e) != null) {
                throw new IllegalArgumentException("duplicate value: " + e.getValue());
            }
        }
    }
    public static PageScanType get(int value) {
        return byValue.get(Integer.valueOf(value));
    }

    private final int value;

    PageScanType(int value)
    {
        this.value = value;
    }

    int getValue() {
        return value;
    }
}
