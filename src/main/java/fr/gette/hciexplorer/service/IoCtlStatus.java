package fr.gette.hciexplorer.service;

import java.util.HashMap;
import java.util.Map;

public enum IoCtlStatus {
    STATUS_SUCCESS(0x00000000L),
    STATUS_CANCELLED(0xC0000120L),
    STATUS_UNFINISHED(null);

    private static final Map<Long, IoCtlStatus> byCode = new HashMap<>();
    static {
        for (IoCtlStatus e : IoCtlStatus.values()) {
            if (byCode.put(e.getCode(), e) != null) {
                throw new IllegalArgumentException("duplicate code: " + e.getCode());
            }
        }
    }
    public static IoCtlStatus get(Long code) {
        return byCode.get(code);
    }

    private final Long code;

    IoCtlStatus(Long code)
    {
        this.code = code;
    }

    Long getCode() {
        return code;
    }
}
