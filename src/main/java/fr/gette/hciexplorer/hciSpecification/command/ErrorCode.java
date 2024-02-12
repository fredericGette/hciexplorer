package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.event.EventCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public enum ErrorCode {
    SUCCESS(0x00),
    UNKNOWN_HCI_COMMAND(0x01),
    UNKNOWN_CONNECTION(0x02),
    HARDWARE_FAILURE(0x03),
    PAGE_TIMEOUT(0x04),
    AUTHENTICATION_FAILURE(0x05),
    PIN_OR_KEY_MISSING(0x06),
    MEMORY_CAPACITY_EXCEEDED(0x07),
    CONNECTION_TIMEOUT(0x08),
    CONNECTION_LIMIT_EXCEEDED(0x09),
    SYNCHRONOUS_CONNECTION_LIMIT_EXCEEDED(0x0A),
    CONNECTION_ALREADY_EXISTS(0x0B),
    COMMAND_DISALLOWED(0x0C),
    CONNECTION_REJECTED_LIMITED_RESOURCES(0x0D),
    CONNECTION_REJECTED_SECURITY_REASONS(0x0E),
    CONNECTION_REJECTED_UNACCEPTABLE_BD_ADDR(0x0F),
    CONNECTION_ACCEPT_TIMEOUT(0x10),
    UNSUPORTED_FEATURE_OR_PARAMETER_VALUE(0x11),
    INVALID_HCI_COMMAND_PARAMETERS(0x12),
    REMOTE_USER_TERMINATED_CONNECTION(0x13),
    REMOTE_DEVICE_TERMINATED_CONNECTION_LOW_RESOURCES(0x14),
    REMOTE_DEVICE_TERMINATED_CONNECTION_POWER_OFF(0x15),
    CONNECTION_TERMINATED_BY_LOCAL_HOST(0x16),
    REPEATED_ATTEMPTS(0x17),
    PAIRING_NOT_ALLOWED(0x18),
    UNKNOWN_LMP_PDU(0x19),
    UNSUPPORTED_REMOTE_OR_LMP_FEATURE(0x1A),
    SCO_OFFSET_REJECTED(0x1B),
    SCO_INTERVAL_REJECTED(0x1C),
    SCO_AIR_MODE_REJECTED(0x1D),
    INVALID_LMP_OR_LL_PARAMETERS(0x1E),
    UNSPECIFIED_ERROR(0x1F),
    UNSUPPORTED_LMP_OR_LL_PARAMETER(0x20),
    ROLE_CHANGE_NOT_ALLOWED(0x21),
    LMP_RESPONSE_TIMEOUT(0x22),
    LINK_LAYER_COLLISION(0x23),
    LMP_PDU_NOT_ALLOWED(0x24),
    ENCRYPTION_MODE_NOT_ACCEPTABLE(0x25),
    UNIT_KEY_USED(0x26),
    QOS_NOT_SUPPORTED(0x27),
    INSTANT_PASSED(0x28),
    PAIRING_WITH_UNIT_KEY_NOT_SUPPORTED(0x29),
    ROLE_SWITCH_FAILED(0x35),
    CONTROLLER_BUSY(0x3A),
    CONNECTION_FAILED_ESTABLISHMENT(0x3E);

    private static final Map<Integer, ErrorCode> byCode = new HashMap<Integer, ErrorCode>();
    static {
        for (ErrorCode e : ErrorCode.values()) {
            if (byCode.put(e.getCode(), e) != null) {
                throw new IllegalArgumentException("duplicate code: " + e.getCode());
            }
        }
    }

    public static ErrorCode get(int code) {
        return byCode.get(Integer.valueOf(code));
    }
    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
