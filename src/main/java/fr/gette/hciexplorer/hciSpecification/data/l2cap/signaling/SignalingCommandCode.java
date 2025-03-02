package fr.gette.hciexplorer.hciSpecification.data.l2cap.signaling;

import java.util.HashMap;
import java.util.Map;

public enum SignalingCommandCode {
    RESERVED(0x00),
    COMMAND_REJECT(0x01),
    CONNECTION_REQUEST(0x02),
    CONNECTION_RESPONSE(0x03),
    CONFIGURATION_REQUEST(0x04),
    CONFIGURATION_RESPONSE(0x05),
    DISCONNECTION_REQUEST(0x06),
    DISCONNECTION_RESPONSE(0x07);

    private static final Map<Integer, SignalingCommandCode> byCode = new HashMap<>();
    static {
        for (SignalingCommandCode e : SignalingCommandCode.values()) {
            if (byCode.put(e.getCode(), e) != null) {
                throw new IllegalArgumentException("duplicate code: " + e.getCode());
            }
        }
    }

    public static SignalingCommandCode get(int code) {
        return byCode.get(Integer.valueOf(code));
    }

    private final int code;

    SignalingCommandCode(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
