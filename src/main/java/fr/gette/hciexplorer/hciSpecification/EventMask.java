package fr.gette.hciexplorer.hciSpecification;

import fr.gette.hciexplorer.hciSpecification.event.EventCode;
import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlMessage;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class EventMask {

    BigInteger value;

    private static final Map<EventCode, Integer> eventBit = new HashMap<>();
    static {
        eventBit.put(EventCode.INQUIRY_COMPLETE, 0*8+1);
        eventBit.put(EventCode.INQUIRY_RESULT, 0*8+2);
        eventBit.put(EventCode.CONNECTION_COMPLETE, 0*8+3);
        eventBit.put(EventCode.CONNECTION_REQUEST, 0*8+4);
        eventBit.put(EventCode.DISCONNECTION_COMPLETE, 0*8+5);
        eventBit.put(EventCode.AUTHENTICATION_COMPLETE, 0*8+6);
        eventBit.put(EventCode.REMOTE_NAME_REQUEST_COMPLETE, 0*8+7);
        eventBit.put(EventCode.ENCRYPTION_CHANGE, 1*8+0);
        eventBit.put(EventCode.CHANGE_CONNECTION_LINK_KEY_COMPLETE, 1*8+1);
        eventBit.put(EventCode.CENTRAL_LINK_KEY_COMPLETE, 1*8+2);
        eventBit.put(EventCode.READ_REMOTE_SUPPORTED_FEATURES_COMPLETE, 1*8+3);
        eventBit.put(EventCode.READ_REMOTE_VERSION_INFORMATION_COMPLETE, 1*8+4);
        eventBit.put(EventCode.QOS_SETUP_COMPLETE, 1*8+5);
        eventBit.put(EventCode.HARDWARE_ERROR, 2*8+0);
        eventBit.put(EventCode.FLUSH_OCCURRED, 2*8+1);
        eventBit.put(EventCode.ROLE_CHANGE, 2*8+2);
        eventBit.put(EventCode.MODE_CHANGE, 2*8+4);
        eventBit.put(EventCode.RETURN_LINK_KEYS, 2*8+5);
        eventBit.put(EventCode.PIN_CODE_REQUEST, 2*8+6);
        eventBit.put(EventCode.LINK_KEY_REQUEST, 2*8+7);
        eventBit.put(EventCode.LINK_KEY_NOTIFICATION, 3*8+0);
        eventBit.put(EventCode.LOOPBACK_COMMAND, 3*8+1);
        eventBit.put(EventCode.DATA_BUFFER_OVERFLOW, 3*8+2);
        eventBit.put(EventCode.MAX_SLOTS_CHANGE, 3*8+3);
        eventBit.put(EventCode.READ_CLOCK_OFFSET_COMPLETE, 3*8+4);
        eventBit.put(EventCode.CONNECTION_PACKET_TYPE_CHANGED, 3*8+5);
        eventBit.put(EventCode.QOS_VIOLATION, 3*8+6);
        eventBit.put(EventCode.PAGE_SCAN_MODE_CHANGE, 3*8+7);
        eventBit.put(EventCode.PAGE_SCAN_REPETITION_MODE_CHANGE, 4*8+0);
        eventBit.put(EventCode.FLOW_SPECIFICATION_COMPLETE, 4*8+1);
        eventBit.put(EventCode.INQUIRY_RESULT_WITH_RSSI, 4*8+2);
        eventBit.put(EventCode.READ_REMOTE_EXTENDED_FEATURES_COMPLETE, 4*8+3);
        eventBit.put(EventCode.SYNCHRONOUS_CONNECTION_COMPLETE, 5*8+4);
        eventBit.put(EventCode.SYNCHRONOUS_CONNECTION_CHANGED, 5*8+5);
        eventBit.put(EventCode.SNIFF_SUBRATING, 5*8+6);
        eventBit.put(EventCode.EXTENDED_INQUIRY_RESULT, 5*8+7);
        eventBit.put(EventCode.ENCRYPTION_KEY_REFRESH_COMPLETE, 6*8+0);
        eventBit.put(EventCode.IO_CAPABILITY_REQUEST, 6*8+1);
        eventBit.put(EventCode.IO_CAPABILITY_RESPONSE, 6*8+2);
        eventBit.put(EventCode.USER_CONFIRMATION_REQUEST, 6*8+3);
        eventBit.put(EventCode.USER_PASSKEY_REQUEST, 6*8+4);
        eventBit.put(EventCode.REMOTE_OOB_DATA_REQUEST, 6*8+5);
        eventBit.put(EventCode.SIMPLE_PAIRING_COMPLETE, 6*8+6);
        eventBit.put(EventCode.LINK_SUPERVISION_TIMEOUT_CHANGED, 7*8+0);
        eventBit.put(EventCode.ENHANCED_FLUSH_COMPLETE, 7*8+1);
        eventBit.put(EventCode.USER_PASSKEY_NOTIFICATION, 7*8+3);
        eventBit.put(EventCode.KEYPRESS_NOTIFICATION, 7*8+4);
        eventBit.put(EventCode.REMOTE_HOST_SUPPORTED_FEATURES_NOTIFICATION, 7*8+5);
        eventBit.put(EventCode.LE_META_EVENT, 7*8+6);
    }

    public EventMask(IoCtlMessage data)
    {
        value = BigInteger.ZERO;
        for (int i=0; i<8; i++)
        {
            BigInteger octet = BigInteger.valueOf(data.read1octet()).shiftLeft(i*8);
            value = value.add(octet);
        }
    }

    private boolean isEnabled(EventCode event)
    {
        int bit = eventBit.get(event);
        return value.testBit(bit);
    }

    public List<EventCode> getEnabledEvents()
    {
        List<EventCode> enabledEvents = new ArrayList<>();

        eventBit.keySet().forEach(event -> {
            if (isEnabled(event))
            {
                enabledEvents.add(event);
            }
        });

        return enabledEvents;
    }

}
