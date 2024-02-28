package fr.gette.hciexplorer.hciSpecification;

import fr.gette.hciexplorer.hciSpecification.event.EventCode;
import fr.gette.hciexplorer.hciSpecification.event.LeEventCode;
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
public class LeEventMask {

    BigInteger value;

    private static final Map<LeEventCode, Integer> eventBit = new HashMap<>();
    static {
        eventBit.put(LeEventCode.LE_CONNECTION_COMPLETE, 0*8+1);
        eventBit.put(LeEventCode.LE_ADVERTISING_REPORT, 0*8+2);
        eventBit.put(LeEventCode.LE_CONNECTION_UPDATE_COMPLETE, 0*8+3);
        eventBit.put(LeEventCode.LE_READ_REMOTE_USED_FEATURES_COMPLETE, 0*8+4);
        eventBit.put(LeEventCode.LE_LONG_TERM_KEY_REQUEST, 0*8+5);
    }

    public LeEventMask(IoCtlMessage data)
    {
        value = BigInteger.ZERO;
        for (int i=0; i<8; i++)
        {
            BigInteger octet = BigInteger.valueOf(data.read1octet()).shiftLeft(i*8);
            value = value.add(octet);
        }
    }

    private boolean isEnabled(LeEventCode event)
    {
        int bit = eventBit.get(event);
        return value.testBit(bit);
    }

    public List<LeEventCode> getEnabledEvents()
    {
        List<LeEventCode> enabledEvents = new ArrayList<>();

        eventBit.keySet().forEach(event -> {
            if (isEnabled(event))
            {
                enabledEvents.add(event);
            }
        });

        return enabledEvents;
    }

}
