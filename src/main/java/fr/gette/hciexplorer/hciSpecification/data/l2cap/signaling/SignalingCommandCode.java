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
    DISCONNECTION_RESPONSE(0x07),
    ECHO_REQUEST(0x08),
    ECHO_RESPONSE(0x09),
    INFORMATION_REQUEST(0x0A),
    INFORMATION_RESPONSE(0x0B),
    CREATE_CHANNEL_REQUEST(0x0C),
    CREATE_CHANNEL_RESPONSE(0x0D),
    MOVE_CHANNEL_REQUEST(0x0E),
    MOVE_CHANNEL_RESPONSE(0x0F),
    MOVE_CHANNEL_CONFIRMATION(0x10),
    MOVE_CHANNEL_CONFIRMATION_RESPONSE(0x11),
    CONNECTION_PARAMETER_UPDATE_REQUEST(0x12),
    CONNECTION_PARAMETER_UPDATE_RESPONSE(0x13);

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
