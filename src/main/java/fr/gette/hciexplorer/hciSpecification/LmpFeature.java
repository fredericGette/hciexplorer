package fr.gette.hciexplorer.hciSpecification;

import fr.gette.hciexplorer.hciSpecification.command.OpcodeGroupField;

import java.util.HashMap;
import java.util.Map;

public enum LmpFeature {
    THREE_SLOT_PACKETS,
    FIVE_SLOT_PACKETS,
    ENCRYPTION,
    SLOT_OFFSET,
    TIMING_ACCURACY,
    ROLE_SWITCH,
    HOLD_MODE,
    SNIFF_MODE,
    PARK_STATE,
    POWER_CONTROL_REQUESTS,
    CHANNEL_QUALITY_DRIVEN_DATA_RATE,
    SCO_LINK,
    HV2_PACKETS,
    HV3_PACKETS,
    MICRO_LAW_LOG_SYNCHRONOUS_DATA,
    A_LAW_LOG_SYNCHRONOUS_DATA,
    CVSD_SYNCHRONOUS_DATA,
    PAGING_PARAMETER_NEGOTIATION,
    POWER_CONTROL,
    TRANSPARENT_SYNCHRONOUS_DATA,
    FLOW_CONTROL_LAG_LEAST_SIGNIFICANT_BIT,
    FLOW_CONTROL_LAG_MIDDLE_BIT,
    FLOW_CONTROL_LAG_MOST_SIGNIFICANT_BIT,
    BROADCAST_ENCRYPTION,
    ENHANCED_DATA_RATE_ACL_2_MB_S_MODE,
    ENHANCED_DATA_RATE_ACL_3_MB_S_MODE,
    ENHANCED_INQUIRY_SCAN,
    INTERLACED_INQUIRY_SCAN,
    INTERLACED_PAGE_SCAN,
    RSSI_WITH_INQUIRY_RESULTS,
    EXTENDED_SCO_LINK_EV3_PACKETS,
    EV4_PACKETS,
    EV5_PACKETS,
    AFH_CAPABLE_PERIPHERAL,
    AFH_CLASSIFICATION_PERIPHERAL,
    BR_EDR_NOT_SUPPORTED,
    LE_SUPPORTED_CONTROLLER,
    THREE_SLOT_ENHANCED_DATA_RATE_ACL_PACKETS,
    FIVE_SLOT_ENHANCED_DATA_RATE_ACL_PACKETS,
    SNIFF_SUBRATING,
    PAUSE_ENCRYPTION,
    AFH_CAPABLE_CENTRAL,
    AFH_CLASSIFICATION_CENTRAL,
    ENHANCED_DATA_RATE_ESCO_2_MB_S_MODE,
    ENHANCED_DATA_RATE_ESCO_3_MB_S_MODE,
    THREE_SLOT_ENHANCED_DATA_RATE_ESCO_PACKETS,
    EXTENDED_INQUIRY_RESPONSE,
    SIMULTANEOUS_LE_AND_BR_EDR_CAPABLE_CONTROLLER,
    SECURE_SIMPLE_PAIRING_CONTROLLER_SUPPORT,
    ENCAPSULATED_PDU,
    ERRONEOUS_DATA_REPORTING,
    NON_FLUSHABLE_PACKET_BOUNDARY_FLAG,
    HCI_LINK_SUPERVISION_TIMEOUT_CHANGED_EVENT,
    VARIABLE_INQUIRY_TX_POWER_LEVEL,
    ENHANCED_POWER_CONTROL,
    EXTENDED_FEATURES;

    }
