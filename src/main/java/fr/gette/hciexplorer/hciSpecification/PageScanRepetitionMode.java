package fr.gette.hciexplorer.hciSpecification;

import java.util.HashMap;
import java.util.Map;

public enum PageScanRepetitionMode
{
    R0(0x00),
    R1(0x01),
    R2(0x02);

    private static final Map<Integer, PageScanRepetitionMode> byValue = new HashMap<>();
    static {
        for (PageScanRepetitionMode e : PageScanRepetitionMode.values()) {
            if (byValue.put(e.getValue(), e) != null) {
                throw new IllegalArgumentException("duplicate value: " + e.getValue());
            }
        }
    }
    public static PageScanRepetitionMode get(int value) {
        return byValue.get(Integer.valueOf(value));
    }

    private final int value;

    PageScanRepetitionMode(int value)
    {
        this.value = value;
    }

    int getValue() {
        return value;
    }
}
