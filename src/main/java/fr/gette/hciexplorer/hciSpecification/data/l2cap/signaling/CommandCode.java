package fr.gette.hciexplorer.hciSpecification.data.l2cap.signaling;

import java.util.HashMap;
import java.util.Map;

public enum CommandCode {
    RESERVED(0x00),
    COMMAND_REJECT(0x01),
    CONNECTION_REQUEST(0x02);

    private static final Map<Integer, CommandCode> byCode = new HashMap<>();
    static {
        for (CommandCode e : CommandCode.values()) {
            if (byCode.put(e.getCode(), e) != null) {
                throw new IllegalArgumentException("duplicate code: " + e.getCode());
            }
        }
    }

    public static CommandCode get(Integer code) {
        return byCode.get(code);
    }

    public static CommandCode get(Short code) {
        return byCode.get(code.intValue());
    }

    private final int code;

    CommandCode(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
