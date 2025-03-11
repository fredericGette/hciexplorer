package fr.gette.hciexplorer.hciSpecification.event.leMetaEvent;

import java.util.HashMap;
import java.util.Map;

public enum LeSubeventCode {
    LE_CONNECTION_COMPLETE_EVENT(0x01),
    LE_ADVERTISING_REPORT_EVENT(0x2),
    LE_CONNECTION_UPDATE_COMPLETE_EVENT(0x3),
    LE_READ_REMOTE_USED_FEATURES_COMPLETE_EVENT(0x4),
    LE_LONG_TERM_KEY_REQUEST_EVENT(0x5);

    private static final Map<Integer, LeSubeventCode> byCode = new HashMap<Integer, LeSubeventCode>();
    static {
        for (LeSubeventCode e : LeSubeventCode.values()) {
            if (byCode.put(e.getCode(), e) != null) {
                throw new IllegalArgumentException("duplicate code: " + e.getCode());
            }
        }
    }

    public static LeSubeventCode get(int code) {
        return byCode.get(Integer.valueOf(code));
    }

    private final int code;

    LeSubeventCode(int code) {
        this.code = code;
    }

    private Integer getCode() {
        return code;
    }
}
