package fr.gette.hciexplorer.hciSpecification.command;

import java.util.HashMap;
import java.util.Map;

public enum OpcodeGroupField {
    LINK_CONTROL(0x01),
    LINK_POLICY(0x02),
    CONTROLLER_AND_BASEBAND(0x03),
    INFORMATIONAL_PARAMETERS(0x04),
    STATUS_PARAMETERS(0x05),
    TESTING(0x06),
    LE_CONTROLLER(0x08),
    BTH_LOGO_TESTING(0x3E),
    VENDOR_SPECIFIC(0x3F);

    private static final Map<Integer, OpcodeGroupField> byCode = new HashMap<>();
    static {
        for (OpcodeGroupField e : OpcodeGroupField.values()) {
            if (byCode.put(e.getCode(), e) != null) {
                throw new IllegalArgumentException("duplicate code: " + e.getCode());
            }
        }
    }

    public static OpcodeGroupField get(int code) {
        return byCode.get(Integer.valueOf(code));
    }
    private final int code;

    OpcodeGroupField(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
