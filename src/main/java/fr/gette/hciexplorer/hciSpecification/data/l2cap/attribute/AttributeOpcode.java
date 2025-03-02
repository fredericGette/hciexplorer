package fr.gette.hciexplorer.hciSpecification.data.l2cap.attribute;

import java.util.HashMap;
import java.util.Map;

public enum AttributeOpcode {
    RESERVED(0x00),
    ERROR_RESPONSE(0x01),
    EXCHANGE_MTU_REQUEST(0x02),
    EXCHANGE_MTU_RESPONSE(0x03),
    FIND_INFORMATION_REQUEST(0x04),
    FIND_INFORMATION_RESPONSE(0x05),
    FIND_BY_TYPE_VALUE_REQUEST(0x06),
    FIND_BY_TYPE_VALUE_RESPONSE(0x07),
    READ_BY_TYPE_REQUEST(0x08),
    READ_BY_TYPE_RESPONSE(0x09),
    READ_REQUEST(0x0A),
    READ_RESPONSE(0x0B),
    READ_BLOB_REQUEST(0x0C),
    READ_BLOB_RESPONSE(0x0D),
    READ_MULTIPLE_REQUEST(0x0E),
    READ_MULTIPLE_RESPONSE(0x0F),
    READ_BY_GROUP_TYPE_REQUEST(0x10),
    READ_BY_GROUP_TYPE_RESPONSE(0x11),
    WRITE_REQUEST(0x12),
    WRITE_RESPONSE(0x13),
    WRITE_COMMAND(0x52),
    SIGNED_WRITE_COMMAND(0xD2);

    private static final Map<Integer, AttributeOpcode> byCode = new HashMap<>();
    static {
        for (AttributeOpcode e : AttributeOpcode.values()) {
            if (byCode.put(e.getCode(), e) != null) {
                throw new IllegalArgumentException("duplicate code: " + e.getCode());
            }
        }
    }

    public static AttributeOpcode get(int code) {
        return byCode.get(Integer.valueOf(code));
    }

    private final int code;

    AttributeOpcode(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
