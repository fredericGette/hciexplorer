package fr.gette.hciexplorer.hciSpecification.data.l2cap.securityManager;

import java.util.HashMap;
import java.util.Map;

public enum SecurityManagerCommandCode {
    RESERVED(0x00),
    PAIRING_REQUEST(0x01),
    PAIRING_RESPONSE(0x02),
    PAIRING_CONFIRM(0x03),
    PAIRING_RANDOM(0x04),
    PAIRING_FAILED(0x05),
    ENCRYPTION_INFORMATION(0x06),
    MASTER_IDENTIFICATION(0x07),
    IDENTITY_INFORMATION(0x08),
    IDENTITY_ADDRESS_INFORMATION(0x09),
    SIGNING_INFORMATION(0x0A),
    SECURITY_REQUEST(0x0B);

    private static final Map<Integer, SecurityManagerCommandCode> byCode = new HashMap<>();
    static {
        for (SecurityManagerCommandCode e : SecurityManagerCommandCode.values()) {
            if (byCode.put(e.getCode(), e) != null) {
                throw new IllegalArgumentException("duplicate code: " + e.getCode());
            }
        }
    }

    public static SecurityManagerCommandCode get(int code) {
        return byCode.get(Integer.valueOf(code));
    }

    private final int code;

    SecurityManagerCommandCode(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
