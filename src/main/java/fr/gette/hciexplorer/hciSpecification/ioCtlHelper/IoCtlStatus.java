package fr.gette.hciexplorer.hciSpecification.ioCtlHelper;

import java.util.HashMap;
import java.util.Map;

public enum IoCtlStatus {
    STATUS_SUCCESS(0x00000000L),
    STATUS_CANCELLED(0xC0000120L);

    private static final Map<Long, IoCtlStatus> byCode = new HashMap<Long, IoCtlStatus>();
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

    private final long code;

    IoCtlStatus(long code)
    {
        this.code = code;
    }

    long getCode() {
        return code;
    }
}
