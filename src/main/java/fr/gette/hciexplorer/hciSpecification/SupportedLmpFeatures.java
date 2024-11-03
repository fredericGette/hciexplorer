package fr.gette.hciexplorer.hciSpecification;

import fr.gette.hciexplorer.hciSpecification.helper.BinaryMessage;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SupportedLmpFeatures {

    BigInteger value;

    private static final Map<LmpFeature, Integer> featureBit = new HashMap<>();
    static {
        featureBit.put(LmpFeature.THREE_SLOT_PACKETS, 0*8+0);
        featureBit.put(LmpFeature.FIVE_SLOT_PACKETS, 0*8+1);
        featureBit.put(LmpFeature.ENCRYPTION, 0*8+2);
        featureBit.put(LmpFeature.SLOT_OFFSET, 0*8+3);
        featureBit.put(LmpFeature.TIMING_ACCURACY, 0*8+4);
        featureBit.put(LmpFeature.ROLE_SWITCH, 0*8+5);
        featureBit.put(LmpFeature.HOLD_MODE, 0*8+6);
        featureBit.put(LmpFeature.SNIFF_MODE, 0*8+7);
        featureBit.put(LmpFeature.PARK_STATE, 1*8+0);
        featureBit.put(LmpFeature.POWER_CONTROL_REQUESTS, 1*8+1);
        featureBit.put(LmpFeature.CHANNEL_QUALITY_DRIVEN_DATA_RATE, 1*8+2);
        featureBit.put(LmpFeature.SCO_LINK, 1*8+3);
        featureBit.put(LmpFeature.HV2_PACKETS, 1*8+4);
        featureBit.put(LmpFeature.HV3_PACKETS, 1*8+5);
        featureBit.put(LmpFeature.MICRO_LAW_LOG_SYNCHRONOUS_DATA, 1*8+6);
        featureBit.put(LmpFeature.A_LAW_LOG_SYNCHRONOUS_DATA, 1*8+7);
        featureBit.put(LmpFeature.CVSD_SYNCHRONOUS_DATA, 2*8+0);
        featureBit.put(LmpFeature.PAGING_PARAMETER_NEGOTIATION, 2*8+1);
        featureBit.put(LmpFeature.POWER_CONTROL, 2*8+2);
        featureBit.put(LmpFeature.TRANSPARENT_SYNCHRONOUS_DATA, 2*8+3);
        featureBit.put(LmpFeature.FLOW_CONTROL_LAG_LEAST_SIGNIFICANT_BIT, 2*8+4);
        featureBit.put(LmpFeature.FLOW_CONTROL_LAG_MIDDLE_BIT, 2*8+5);
        featureBit.put(LmpFeature.FLOW_CONTROL_LAG_MOST_SIGNIFICANT_BIT, 2*8+6);
        featureBit.put(LmpFeature.BROADCAST_ENCRYPTION, 2*8+7);
        featureBit.put(LmpFeature.ENHANCED_DATA_RATE_ACL_2_MB_S_MODE, 3*8+1);
        featureBit.put(LmpFeature.ENHANCED_DATA_RATE_ACL_3_MB_S_MODE, 3*8+2);
        featureBit.put(LmpFeature.ENHANCED_INQUIRY_SCAN, 3*8+3);
        featureBit.put(LmpFeature.INTERLACED_INQUIRY_SCAN, 3*8+4);
        featureBit.put(LmpFeature.INTERLACED_PAGE_SCAN, 3*8+5);
        featureBit.put(LmpFeature.RSSI_WITH_INQUIRY_RESULTS, 3*8+6);
        featureBit.put(LmpFeature.EXTENDED_SCO_LINK_EV3_PACKETS, 3*8+7);
        featureBit.put(LmpFeature.EV4_PACKETS, 4*8+0);
        featureBit.put(LmpFeature.EV5_PACKETS, 4*8+1);
        featureBit.put(LmpFeature.AFH_CAPABLE_PERIPHERAL, 4*8+3);
        featureBit.put(LmpFeature.AFH_CLASSIFICATION_PERIPHERAL, 4*8+4);
        featureBit.put(LmpFeature.BR_EDR_NOT_SUPPORTED, 4*8+5);
        featureBit.put(LmpFeature.LE_SUPPORTED_CONTROLLER, 4*8+6);
        featureBit.put(LmpFeature.THREE_SLOT_ENHANCED_DATA_RATE_ACL_PACKETS, 4*8+7);
        featureBit.put(LmpFeature.FIVE_SLOT_ENHANCED_DATA_RATE_ACL_PACKETS, 5*8+0);
        featureBit.put(LmpFeature.SNIFF_SUBRATING, 5*8+1);
        featureBit.put(LmpFeature.PAUSE_ENCRYPTION, 5*8+2);
        featureBit.put(LmpFeature.AFH_CAPABLE_CENTRAL, 5*8+3);
        featureBit.put(LmpFeature.AFH_CLASSIFICATION_CENTRAL, 5*8+4);
        featureBit.put(LmpFeature.ENHANCED_DATA_RATE_ESCO_2_MB_S_MODE, 5*8+5);
        featureBit.put(LmpFeature.ENHANCED_DATA_RATE_ESCO_3_MB_S_MODE, 5*8+6);
        featureBit.put(LmpFeature.THREE_SLOT_ENHANCED_DATA_RATE_ESCO_PACKETS, 5*8+7);
        featureBit.put(LmpFeature.EXTENDED_INQUIRY_RESPONSE, 6*8+0);
        featureBit.put(LmpFeature.SIMULTANEOUS_LE_AND_BR_EDR_CAPABLE_CONTROLLER, 6*8+1);
        featureBit.put(LmpFeature.SECURE_SIMPLE_PAIRING_CONTROLLER_SUPPORT, 6*8+3);
        featureBit.put(LmpFeature.ENCAPSULATED_PDU, 6*8+4);
        featureBit.put(LmpFeature.ERRONEOUS_DATA_REPORTING, 6*8+5);
        featureBit.put(LmpFeature.NON_FLUSHABLE_PACKET_BOUNDARY_FLAG, 6*8+6);
        featureBit.put(LmpFeature.HCI_LINK_SUPERVISION_TIMEOUT_CHANGED_EVENT, 7*8+0);
        featureBit.put(LmpFeature.VARIABLE_INQUIRY_TX_POWER_LEVEL, 7*8+1);
        featureBit.put(LmpFeature.ENHANCED_POWER_CONTROL, 7*8+2);
        featureBit.put(LmpFeature.EXTENDED_FEATURES, 7*8+7);
    }

    public SupportedLmpFeatures(BinaryMessage data)
    {
        value = BigInteger.ZERO;
        for (int i=0; i<8; i++)
        {
            BigInteger octet = BigInteger.valueOf(data.read1octet()).shiftLeft(i*8);
            value = value.add(octet);
        }
    }

    private boolean isSupported(LmpFeature feature)
    {
        int bit = featureBit.get(feature);
        return value.testBit(bit);
    }

    public List<LmpFeature> getFeatures()
    {
        List<LmpFeature> supportedLmpFeatures = new ArrayList<>();

        featureBit.keySet().forEach(feature -> {
            if (isSupported(feature))
            {
                supportedLmpFeatures.add(feature);
            }
        });

        return supportedLmpFeatures;
    }

}
