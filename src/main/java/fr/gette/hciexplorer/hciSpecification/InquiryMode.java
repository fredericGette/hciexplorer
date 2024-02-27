package fr.gette.hciexplorer.hciSpecification;

import java.util.HashMap;
import java.util.Map;

public enum InquiryMode
{
    STANDARD_INQUIRY_RESULT(0x00),
    INQUIRY_RESULT_WITH_RSSI(0x01),
    INQUIRY_RESULT_WITH_RSSI_OR_EXTENDED_INQUIRY_RESULT(0x02);

    private static final Map<Integer, InquiryMode> byValue = new HashMap<>();
    static {
        for (InquiryMode e : InquiryMode.values()) {
            if (byValue.put(e.getValue(), e) != null) {
                throw new IllegalArgumentException("duplicate value: " + e.getValue());
            }
        }
    }
    public static InquiryMode get(int value) {
        return byValue.get(Integer.valueOf(value));
    }

    private final int value;

    InquiryMode(int value)
    {
        this.value = value;
    }

    int getValue() {
        return value;
    }
}
