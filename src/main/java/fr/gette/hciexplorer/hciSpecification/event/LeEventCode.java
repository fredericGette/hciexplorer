package fr.gette.hciexplorer.hciSpecification.event;

import java.util.HashMap;
import java.util.Map;

public enum LeEventCode {
    LE_CONNECTION_COMPLETE(0x01,"Indicates to both hosts that the new connection has been formed."),
    LE_ADVERTISING_REPORT(0x02,""),
    LE_CONNECTION_UPDATE_COMPLETE(0x03,""),
    LE_READ_REMOTE_USED_FEATURES_COMPLETE(0x04,""),
    LE_LONG_TERM_KEY_REQUEST(0x05,"");

    private static final Map<Integer, LeEventCode> byCode = new HashMap<Integer, LeEventCode>();
    static {
        for (LeEventCode e : LeEventCode.values()) {
            if (byCode.put(e.getCode(), e) != null) {
                throw new IllegalArgumentException("duplicate code: " + e.getCode());
            }
        }
    }

    public static LeEventCode get(int code) {
        return byCode.get(Integer.valueOf(code));
    }

    private final int code;
    private final String description;

    LeEventCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    private Integer getCode() {
        return code;
    }

}
