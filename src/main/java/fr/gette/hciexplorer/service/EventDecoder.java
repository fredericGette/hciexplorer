package fr.gette.hciexplorer.service;

import fr.gette.hciexplorer.entity.BeginReadRawMessage;
import fr.gette.hciexplorer.entity.EndReadRawMessage;
import fr.gette.hciexplorer.hciSpecification.*;
import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import fr.gette.hciexplorer.hciSpecification.event.*;
import fr.gette.hciexplorer.hciSpecification.event.commandComplete.*;
import fr.gette.hciexplorer.hciSpecification.event.leMetaEvent.LeConnectionCompleteEvent;
import fr.gette.hciexplorer.hciSpecification.event.leMetaEvent.LeLongTermKeyRequestEvent;
import fr.gette.hciexplorer.hciSpecification.event.leMetaEvent.LeSubeventCode;
import fr.gette.hciexplorer.hciSpecification.helper.BinaryMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventDecoder {

    public HciMessage decode(BinaryMessage endData)
    {
        endData.resetOffset();
        return decode(endData, IoCtlStatus.STATUS_SUCCESS);
    }

    public HciMessage decode(BeginReadRawMessage begin, EndReadRawMessage end)
    {
        BinaryMessage endData = new BinaryMessage(end.getOutputBuffer());
        IoCtlStatus ioCtlStatus = IoCtlStatus.get(end.getStatus());

        return decode(endData, ioCtlStatus);
    }

    private HciMessage decode(BinaryMessage endData, IoCtlStatus ioCtlStatus) {
        HciMessage hciMsg;

        switch (ioCtlStatus)
        {
            case STATUS_SUCCESS -> {
                long size = endData.read4octets(); // size of the HCI packet
                HciPacketType hciPacketTypeEnd = HciPacketType.get(endData.read1octet());
                if (null != hciPacketTypeEnd && !HciPacketType.EVENT.equals(hciPacketTypeEnd))
                {
                    throw new IllegalArgumentException(String.format(
                            "Expected packet type 'EVENT' (0x04), found %s",hciPacketTypeEnd));
                }

                EventCode eventCode = EventCode.get(endData.read1octet());
                short payloadLength = endData.read1octet();

                hciMsg = build(eventCode, endData);
            }
            case STATUS_CANCELLED -> {
                EventCanceled eventCanceled = new EventCanceled();
                eventCanceled.setIoCtlStatus(ioCtlStatus);
                hciMsg = eventCanceled;
            }
            case STATUS_UNFINISHED -> {
                EventUnfinished eventUnfinished = new EventUnfinished();
                eventUnfinished.setIoCtlStatus(ioCtlStatus);
                hciMsg = eventUnfinished;
            }
            default -> throw new UnsupportedOperationException(
                    String.format("Status : %s",ioCtlStatus));
        }

        return hciMsg;
    }

    private Event build(EventCode eventCode, BinaryMessage data)
    {
        Event event;

        switch (eventCode)
        {
            case COMMAND_COMPLETE -> event = buildCommandComplete(data);
            case COMMAND_STATUS -> event = buildCommandStatus(data);
            case CONNECTION_COMPLETE -> event = buildConnectionComplete(data);
            case EXTENDED_INQUIRY_RESULT -> event = buildExtendedInquiryResult(data);
            case INQUIRY_RESULT_WITH_RSSI -> event = buildInquiryResultWithRssi(data);
            case INQUIRY_COMPLETE -> event = buildInquiryComplete(data);
            case REMOTE_NAME_REQUEST_COMPLETE -> event = buildRemoteNameRequestComplete(data);
            case READ_REMOTE_SUPPORTED_FEATURES_COMPLETE -> event = buildReadRemoteSupportedFeaturesComplete(data);
            case NUMBER_OF_COMPLETED_PACKETS -> event = buildNumberOfCompletedPackets(data);
            case READ_REMOTE_VERSION_INFORMATION_COMPLETE -> event = buildReadRemoteVersionInformationComplete(data);
            case READ_CLOCK_OFFSET_COMPLETE -> event = buildReadClockOffsetComplete(data);
            case QOS_SETUP_COMPLETE -> event = buildQosSetupComplete(data);
            case DISCONNECTION_COMPLETE -> event = buildDisconnectionComplete(data);
            case LINK_KEY_REQUEST -> event = buildLinkKeyRequest(data);
            case PIN_CODE_REQUEST -> event = buildPinCodeRequest(data);
            case LINK_KEY_NOTIFICATION -> event = buildLinkKeyNotification(data);
            case AUTHENTICATION_COMPLETE -> event = buildAuthenticationComplete(data);
            case ENCRYPTION_CHANGE -> event = buildEncryptionChange(data);
            case INQUIRY_RESULT -> event = buildInquiryResult(data);
            case LE_META_EVENT -> event = buildLeMetaEvent(data);
            default -> throw new UnsupportedOperationException(
                    String.format("Event code: %s",eventCode));
        }

        return event;
    }

    private EventCommandComplete buildCommandComplete(BinaryMessage data)
    {
        EventCommandComplete event;
        short numHciCommandPackets = data.read1octet();
        CommandCode commandOpcode = CommandCode.get(data.read2octets());
        switch(commandOpcode)
        {
            case WRITE_CLASS_OF_DEVICE -> event = buildWriteClassOfDeviceComplete(data);
            case RESET -> event = buildResetComplete(data);
            case READ_BD_ADDR -> event = buildReadBdAddrComplete(data);
            case READ_LOCAL_SUPPORTED_COMMANDS -> event = buildReadLocalSupportedCommandsComplete(data);
            case READ_BUFFER_SIZE -> event = buildReadBufferSizeComplete(data);
            case READ_LOCAL_VERSION_INFORMATION -> event = buildReadLocalVersionInformationComplete(data);
            case READ_LOCAL_SUPPORTED_FEATURES -> event = buildReadLocalSupportedFeatureComplete(data);
            case WRITE_SIMPLE_PAIRING_MODE -> event = buildWriteSimplePairingModeComplete(data);
            case READ_LOCAL_OOB_DATA -> event = buildReadLocalOobDataComplete(data);
            case WRITE_SIMPLE_PAIRING_DEBUG_MODE -> event = buildWriteSimplePairingDebugModeComplete(data);
            case WRITE_AUTHENTICATION_ENABLE -> event = buildWriteAuthenticationEnableComplete(data);
            case SET_EVENT_MASK -> event = buildSetEventMaskComplete(data);
            case READ_INQUIRY_RESPONSE_TRANSMIT_POWER_LEVEL -> event = buildReadInquiryResponseTransmitPowerLevelComplete(data);
            case WRITE_PAGE_TIMEOUT -> event = buildWritePageTimeoutComplete(data);
            case WRITE_PAGE_SCAN_ACTIVITY -> event = buildWritePageScanActivityComplete(data);
            case WRITE_PAGE_SCAN_TYPE -> event = buildWritePageScanTypeComplete(data);
            case WRITE_INQUIRY_SCAN_ACTIVITY -> event = buildWriteInquiryScanActivityComplete(data);
            case WRITE_INQUIRY_SCAN_TYPE -> event = buildWriteInquiryScanTypeComplete(data);
            case WRITE_INQUIRY_MODE -> event = buildWriteInquiryModeComplete(data);
            case WRITE_EXTENDED_INQUIRY_RESPONSE -> event = buildWriteExtendedInquiryResponseComplete(data);
            case HOST_BUFFER_SIZE -> event = buildHostBufferSizeComplete(data);
            case WRITE_LOCAL_NAME -> event = buildWriteLocalNameComplete(data);
            case LE_READ_LOCAL_SUPPORTED_FEATURES -> event = buildLeReadLocalSupportedFeaturesComplete(data);
            case LE_READ_SUPPORTED_STATES -> event = buildLeReadSupportedStatesComplete(data);
            case LE_READ_BUFFER_SIZE_V1 -> event = buildLeReadBufferSizeV1Complete(data);
            case LE_READ_CONNECT_LIST_SIZE -> event = buildLeReadConnectListSizeComplete(data);
            case WRITE_LE_HOST_SUPPORT -> event = buildWriteLeHostSupportComplete(data);
            case LE_READ_ADVERTISING_PHYSICAL_CHANNEL_TX_POWER -> event = buildLeReadAdvertisingPhysicalChannelTxPowerComplete(data);
            case LE_SET_EVENT_MASK -> event = buildLeSetEventMaskComplete(data);
            case WRITE_SCAN_ENABLE -> event = buildWriteScanEnableComplete(data);
            case WRITE_INQUIRY_TRANSMIT_POWER_LEVEL -> event = buildWriteInquiryTransmitPowerLevelComplete(data);
            case READ_RSSI -> event = buildReadRssiComplete(data);
            case ROLE_DISCOVERY -> event = buildRoleDiscoveryComplete(data);
            case WRITE_AUTOMATIC_FLUSH_TIMEOUT -> event = buildWriteAutomaticFlushTimeoutComplete(data);
            case READ_TRANSMIT_POWER_LEVEL -> event = buildReadTransmitPowerLevelComplete(data);
            case READ_LINK_QUALITY -> event = buildReadLinkQualityComplete(data);
            case READ_LINK_SUPERVISION_TIMEOUT -> event = buildReadLinkSupervisionTimeoutComplete(data);
            case WRITE_LINK_POLICY_SETTINGS -> event = buildWriteLinkPolicySettingsComplete(data);
            case INQUIRY_CANCEL -> event = buildInquiryCancelComplete(data);
            case LINK_KEY_REQUEST_NEGATIVE_REPLY -> event = buildLinkKeyRequestNegativeReplyComplete(data);
            case PIN_CODE_REQUEST_REPLY -> event = buildPinCodeRequestReplyComplete(data);
            case WRITE_LINK_SUPERVISION_TIMEOUT -> event = buildWriteLinkSupervisionTimeoutComplete(data);
            case PERIODIC_INQUIRY_MODE -> event = buildPeriodicInquiryModeComplete(data);
            case EXIT_PERIODIC_INQUIRY_MODE -> event = buildExitPeriodicInquiryModeComplete(data);
            case LE_SET_ADVERTISE_ENABLE -> event = buildLeSetAdvertiseEnable(data);
            case LE_SET_ADVERTISING_PARAMETERS -> event = buildLeSetAdvertisingParameters(data);
            case LE_SET_ADVERTISING_DATA -> event = buildLeSetAdvertisingData(data);
            default -> throw new UnsupportedOperationException(
                    String.format("Command Opcode : %s",commandOpcode));
        }
        event.setNumHciCommandPackets(numHciCommandPackets);
        return event;
    }

    private EventCommandStatus buildCommandStatus(BinaryMessage data)
    {
        EventCommandStatus event = new EventCommandStatus();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setNumHciCommandPackets(data.read1octet());
        event.setCommandOpCode(CommandCode.get(data.read2octets()));
        return event;
    }

    private EventConnectionComplete buildConnectionComplete(BinaryMessage data)
    {
        EventConnectionComplete event = new EventConnectionComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setConnectionHandle(data.read2octets());
        event.setBdAddr(new BluetoothAddress(data));
        event.setLinkType(LinkType.get(data.read1octet()));
        event.setEncryptionEnabled(EncryptionEnabled.get(data.read1octet()));
        return event;
    }

    private EventExtendedInquiryResult buildExtendedInquiryResult(BinaryMessage data)
    {
        EventExtendedInquiryResult event = new EventExtendedInquiryResult();
        event.setNumResponses(data.read1octet());
        event.setBdAddr(new BluetoothAddress(data));
        event.setPageScanRepetitionMode(PageScanRepetitionMode.get(data.read1octet()));
        data.read1octet(); // reserved
        event.setClassOfDevice(new ClassOfDevice(data));
        event.setClockOffset(data.read2octets());
        event.setRssi(data.read1signedOctet());
        event.setExtendedInquiryResponse(new ExtendedInquiryResponse(data));
        return event;
    }

    private EventInquiryResultWithRssi buildInquiryResultWithRssi(BinaryMessage data)
    {
        EventInquiryResultWithRssi event = new EventInquiryResultWithRssi();
        event.setNumResponses(data.read1octet());
        List<EventInquiryResultWithRssi.SingleInquiryResultWithRssi> results = new ArrayList<>();
        for (int i=0; i<event.getNumResponses(); i++) {
            EventInquiryResultWithRssi.SingleInquiryResultWithRssi result = new EventInquiryResultWithRssi.SingleInquiryResultWithRssi();
            result.setBdAddr(new BluetoothAddress(data));
            result.setPageScanRepetitionMode(PageScanRepetitionMode.get(data.read1octet()));
            data.read1octet(); // reserved
            result.setClassOfDevice(new ClassOfDevice(data));
            result.setClockOffset(data.read2octets());
            result.setRssi(data.read1signedOctet());
            results.add(result);
        }
        event.setInquiryResultWithRssi(results);
        return event;
    }

    private EventInquiryComplete buildInquiryComplete(BinaryMessage data)
    {
        EventInquiryComplete event = new EventInquiryComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private EventRemoteNameRequestComplete buildRemoteNameRequestComplete(BinaryMessage data)
    {
        EventRemoteNameRequestComplete event = new EventRemoteNameRequestComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setBdAddr(new BluetoothAddress(data));
        event.setRemoteName(data.readNullTerminatedString(248));
        return event;
    }

    private EventReadRemoteSupportedFeaturesComplete buildReadRemoteSupportedFeaturesComplete(BinaryMessage data)
    {
        EventReadRemoteSupportedFeaturesComplete event = new EventReadRemoteSupportedFeaturesComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setConnectionHandle(data.read2octets());
        event.setLmpFeatures(new SupportedLmpFeatures(data));
        return event;
    }

    private EventNumberOfCompletedPackets buildNumberOfCompletedPackets(BinaryMessage data)
    {
        EventNumberOfCompletedPackets event = new EventNumberOfCompletedPackets();
        event.setNumberOfHandles(data.read1octet());
        List<Integer> connectionHandles = new ArrayList<>();
        for (int i=0; i< event.getNumberOfHandles(); i++)
        {
            connectionHandles.add(data.read2octets());
        }
        List<Integer> hcNumOfCompletedPackets = new ArrayList<>();
        for (int i=0; i< event.getNumberOfHandles(); i++)
        {
            hcNumOfCompletedPackets.add(data.read2octets());
        }
        event.setConnectionHandle(connectionHandles);
        event.setHcNumOfCompletedPackets(hcNumOfCompletedPackets);
        return event;
    }

    private EventReadRemoteVersionInformationComplete buildReadRemoteVersionInformationComplete(BinaryMessage data)
    {
        EventReadRemoteVersionInformationComplete event = new EventReadRemoteVersionInformationComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setConnectionHandle(data.read2octets());
        event.setVersion(data.read1octet());
        event.setManufacturerName(data.read2octets());
        event.setSubversion(data.read2octets());
        return event;
    }

    private EventReadClockOffsetComplete buildReadClockOffsetComplete(BinaryMessage data)
    {
        EventReadClockOffsetComplete event = new EventReadClockOffsetComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setConnectionHandle(data.read2octets());
        event.setClockOffset(data.read2octets());
        return event;
    }

    private EventQosSetupComplete buildQosSetupComplete(BinaryMessage data)
    {
        EventQosSetupComplete event = new EventQosSetupComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setConnectionHandle(data.read2octets());
        event.setFlags(data.read1octet());
        event.setServiceType(ServiceType.get(data.read1octet()));
        event.setTokenRate(data.read4octets());
        event.setPeakBandwidth(data.read4octets());
        event.setLatency(data.read4octets());
        event.setDelayVariation(data.read4octets());
        return event;
    }

    private EventDisconnectionComplete buildDisconnectionComplete(BinaryMessage data)
    {
        EventDisconnectionComplete event = new EventDisconnectionComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setConnectionHandle(data.read2octets());
        event.setReason(ErrorCode.get(data.read1octet()));
        return event;
    }

    private EventLinkKeyRequest buildLinkKeyRequest(BinaryMessage data)
    {
        EventLinkKeyRequest event = new EventLinkKeyRequest();
        event.setBdAddr(new BluetoothAddress(data));
        return event;
    }

    private EventPinCodeRequest buildPinCodeRequest(BinaryMessage data)
    {
        EventPinCodeRequest event = new EventPinCodeRequest();
        event.setBdAddr(new BluetoothAddress(data));
        return event;
    }

    private EventLinkKeyNotification buildLinkKeyNotification(BinaryMessage data)
    {
        EventLinkKeyNotification event = new EventLinkKeyNotification();
        event.setBdAddr(new BluetoothAddress(data));
        event.setLinkKey(new LinkKey(data));
        event.setKeyType(KeyType.get(data.read1octet()));
        return event;
    }

    private EventAuthenticationComplete buildAuthenticationComplete(BinaryMessage data)
    {
        EventAuthenticationComplete event = new EventAuthenticationComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setConnectionHandle(data.read2octets());
        return event;
    }

    private EventEncryptionChange buildEncryptionChange(BinaryMessage data)
    {
        EventEncryptionChange event = new EventEncryptionChange();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setConnectionHandle(data.read2octets());
        event.setEncryptionEnabled(EncryptionEnabled.get(data.read1octet()));
        return event;
    }

    private WriteClassOfDeviceComplete buildWriteClassOfDeviceComplete(BinaryMessage data)
    {
        WriteClassOfDeviceComplete event = new WriteClassOfDeviceComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private ResetComplete buildResetComplete(BinaryMessage data)
    {
        ResetComplete event = new ResetComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private ReadBdAddrComplete buildReadBdAddrComplete(BinaryMessage data)
    {
        ReadBdAddrComplete event = new ReadBdAddrComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setBdAddr(new BluetoothAddress(data));
        return event;
    }

    private ReadLocalSupportedCommandsComplete buildReadLocalSupportedCommandsComplete(BinaryMessage data)
    {
        ReadLocalSupportedCommandsComplete event = new ReadLocalSupportedCommandsComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setSupportedCommands(new SupportedCommands(data));
        return event;
    }

    private ReadBufferSizeComplete buildReadBufferSizeComplete(BinaryMessage data)
    {
        ReadBufferSizeComplete event = new ReadBufferSizeComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setAclDataPacketLength(data.read2octets());
        event.setSynchronousDataPacketLength(data.read1octet());
        event.setTotalNumACLDataPackets(data.read2octets());
        event.setTotalNumSynchronousDataPackets(data.read2octets());
        return event;
    }

    private ReadLocalVersionInformationComplete buildReadLocalVersionInformationComplete(BinaryMessage data)
    {
        ReadLocalVersionInformationComplete event = new ReadLocalVersionInformationComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setHciVersion(data.read1octet());
        event.setHciSubversion(data.read2octets());
        event.setLmpVersion(data.read1octet());
        event.setCompanyIdentifier(data.read2octets());
        event.setLmpSubversion(data.read2octets());
        return event;
    }

    private ReadLocalSupportedFeatureComplete buildReadLocalSupportedFeatureComplete(BinaryMessage data)
    {
        ReadLocalSupportedFeatureComplete event = new ReadLocalSupportedFeatureComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setLmpFeatures(new SupportedLmpFeatures(data));
        return event;
    }

    private WriteSimplePairingModeComplete buildWriteSimplePairingModeComplete(BinaryMessage data)
    {
        WriteSimplePairingModeComplete event = new WriteSimplePairingModeComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private ReadLocalOobDataComplete buildReadLocalOobDataComplete(BinaryMessage data)
    {
        ReadLocalOobDataComplete event = new ReadLocalOobDataComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setHashC(data);
        event.setRandomizerR(data);
        return event;
    }

    private WriteSimplePairingDebugModeComplete buildWriteSimplePairingDebugModeComplete(BinaryMessage data)
    {
        WriteSimplePairingDebugModeComplete event = new WriteSimplePairingDebugModeComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private WriteAuthenticationEnableComplete buildWriteAuthenticationEnableComplete(BinaryMessage data)
    {
        WriteAuthenticationEnableComplete event = new WriteAuthenticationEnableComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private SetEventMaskComplete buildSetEventMaskComplete(BinaryMessage data)
    {
        SetEventMaskComplete event = new SetEventMaskComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private ReadInquiryResponseTransmitPowerLevelComplete buildReadInquiryResponseTransmitPowerLevelComplete(BinaryMessage data)
    {
        ReadInquiryResponseTransmitPowerLevelComplete event = new ReadInquiryResponseTransmitPowerLevelComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setTxPower(data.read1signedOctet());
        return event;
    }

    private WritePageTimeoutComplete buildWritePageTimeoutComplete(BinaryMessage data)
    {
        WritePageTimeoutComplete event = new WritePageTimeoutComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private WritePageScanActivityComplete buildWritePageScanActivityComplete(BinaryMessage data)
    {
        WritePageScanActivityComplete event = new WritePageScanActivityComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private WritePageScanTypeComplete buildWritePageScanTypeComplete(BinaryMessage data)
    {
        WritePageScanTypeComplete event = new WritePageScanTypeComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private WriteInquiryScanActivityComplete buildWriteInquiryScanActivityComplete(BinaryMessage data)
    {
        WriteInquiryScanActivityComplete event = new WriteInquiryScanActivityComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private WriteInquiryScanTypeComplete buildWriteInquiryScanTypeComplete(BinaryMessage data)
    {
        WriteInquiryScanTypeComplete event = new WriteInquiryScanTypeComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private WriteInquiryModeComplete buildWriteInquiryModeComplete(BinaryMessage data)
    {
        WriteInquiryModeComplete event = new WriteInquiryModeComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private WriteExtendedInquiryResponseComplete buildWriteExtendedInquiryResponseComplete(BinaryMessage data)
    {
        WriteExtendedInquiryResponseComplete event = new WriteExtendedInquiryResponseComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private HostBufferSizeComplete buildHostBufferSizeComplete(BinaryMessage data)
    {
        HostBufferSizeComplete event = new HostBufferSizeComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private WriteLocalNameComplete buildWriteLocalNameComplete(BinaryMessage data)
    {
        WriteLocalNameComplete event = new WriteLocalNameComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private LeReadLocalSupportedFeaturesComplete buildLeReadLocalSupportedFeaturesComplete(BinaryMessage data)
    {
        LeReadLocalSupportedFeaturesComplete event = new LeReadLocalSupportedFeaturesComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setLeFeatures(new SupportedLeFeatures(data));
        return event;
    }

    private LeReadSupportedStatesComplete buildLeReadSupportedStatesComplete(BinaryMessage data)
    {
        LeReadSupportedStatesComplete event = new LeReadSupportedStatesComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setLeStates(new SupportedLeStates((data)));
        return event;
    }

    private LeReadBufferSizeV1Complete buildLeReadBufferSizeV1Complete(BinaryMessage data)
    {
        LeReadBufferSizeV1Complete event = new LeReadBufferSizeV1Complete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setHcLeDataPacketLength(data.read2octets());
        event.setHcTotalNumLeDataPackets(data.read1octet());
        return event;
    }

    private LeReadConnectListSizeComplete buildLeReadConnectListSizeComplete(BinaryMessage data)
    {
        LeReadConnectListSizeComplete event = new LeReadConnectListSizeComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setWhiteListSize(data.read1octet());
        return event;
    }

    private WriteLeHostSupportComplete buildWriteLeHostSupportComplete(BinaryMessage data)
    {
        WriteLeHostSupportComplete event = new WriteLeHostSupportComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private LeReadAdvertisingPhysicalChannelTxPowerComplete buildLeReadAdvertisingPhysicalChannelTxPowerComplete(BinaryMessage data)
    {
        LeReadAdvertisingPhysicalChannelTxPowerComplete event = new LeReadAdvertisingPhysicalChannelTxPowerComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setTransmitPowerLevel(data.read1signedOctet());
        return event;
    }

    private LeSetEventMaskComplete buildLeSetEventMaskComplete(BinaryMessage data)
    {
        LeSetEventMaskComplete event = new LeSetEventMaskComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private WriteScanEnableComplete buildWriteScanEnableComplete(BinaryMessage data)
    {
        WriteScanEnableComplete event = new WriteScanEnableComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private WriteInquiryTransmitPowerLevelComplete buildWriteInquiryTransmitPowerLevelComplete(BinaryMessage data)
    {
        WriteInquiryTransmitPowerLevelComplete event = new WriteInquiryTransmitPowerLevelComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private ReadRssiComplete buildReadRssiComplete(BinaryMessage data)
    {
        ReadRssiComplete event = new ReadRssiComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setHandle(data.read2octets());
        event.setRssi(data.read1signedOctet());
        return event;
    }

    private RoleDiscoveryComplete buildRoleDiscoveryComplete(BinaryMessage data)
    {
        RoleDiscoveryComplete event = new RoleDiscoveryComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setConnectionHandle(data.read2octets());
        event.setCurrentRole(CurrentRole.get(data.read1octet()));
        return event;
    }

    private WriteAutomaticFlushTimeoutComplete buildWriteAutomaticFlushTimeoutComplete(BinaryMessage data)
    {
        WriteAutomaticFlushTimeoutComplete event = new WriteAutomaticFlushTimeoutComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setConnectionHandle(data.read2octets());
        return event;
    }

    private ReadTransmitPowerLevelComplete buildReadTransmitPowerLevelComplete(BinaryMessage data)
    {
        ReadTransmitPowerLevelComplete event = new ReadTransmitPowerLevelComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setConnectionHandle(data.read2octets());
        event.setTransmitPowerLevel(data.read1signedOctet());
        return event;
    }

    private ReadLinkQualityComplete buildReadLinkQualityComplete(BinaryMessage data)
    {
        ReadLinkQualityComplete event = new ReadLinkQualityComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setHandle(data.read2octets());
        event.setLinkQuality(data.read1octet());
        return event;
    }

    private ReadLinkSupervisionTimeoutComplete buildReadLinkSupervisionTimeoutComplete(BinaryMessage data)
    {
        ReadLinkSupervisionTimeoutComplete event = new ReadLinkSupervisionTimeoutComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setHandle(data.read2octets());
        event.setLinkSupervisionTimeout(data.read2octets());
        return event;
    }

    private WriteLinkPolicySettingsComplete buildWriteLinkPolicySettingsComplete(BinaryMessage data)
    {
        WriteLinkPolicySettingsComplete event = new WriteLinkPolicySettingsComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setConnectionHandle(data.read2octets());
        return event;
    }

    private InquiryCancelComplete buildInquiryCancelComplete(BinaryMessage data)
    {
        InquiryCancelComplete event = new InquiryCancelComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private LinkKeyRequestNegativeReplyComplete buildLinkKeyRequestNegativeReplyComplete(BinaryMessage data)
    {
        LinkKeyRequestNegativeReplyComplete event = new LinkKeyRequestNegativeReplyComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setBdAddr(new BluetoothAddress(data));
        return event;
    }

    private PinCodeRequestReplyComplete buildPinCodeRequestReplyComplete(BinaryMessage data)
    {
        PinCodeRequestReplyComplete event = new PinCodeRequestReplyComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setBdAddr(new BluetoothAddress(data));
        return event;
    }


    private WriteLinkSupervisionTimeoutComplete buildWriteLinkSupervisionTimeoutComplete(BinaryMessage data)
    {
        WriteLinkSupervisionTimeoutComplete event = new WriteLinkSupervisionTimeoutComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setHandle(data.read2octets());
        return event;
    }

    private EventInquiryResult buildInquiryResult(BinaryMessage data)
    {
        EventInquiryResult event = new EventInquiryResult();
        event.setNumResponses(data.read1octet());
        List<EventInquiryResult.SingleInquiryResult> results = new ArrayList<>();
        for (int i=0; i<event.getNumResponses(); i++) {
            EventInquiryResult.SingleInquiryResult result = new EventInquiryResult.SingleInquiryResult();
            result.setBdAddr(new BluetoothAddress(data));
            result.setPageScanRepetitionMode(PageScanRepetitionMode.get(data.read1octet()));
            data.read1octet(); // reserved. It was the Page_Scan_Period_Mode parameter in the v1.1 specification.
            data.read1octet(); // reserved. It was the Page_Scan_Mode parameter in the v1.1 specification.
            result.setClassOfDevice(new ClassOfDevice(data));
            result.setClockOffset(data.read2octets());
            results.add(result);
        }
        event.setInquiryResult(results);
        return event;
    }

    private Event buildLeMetaEvent(BinaryMessage data) {
        Event event;
        LeSubeventCode subeventCode = LeSubeventCode.get(data.read1octet());
        switch (subeventCode) {
            case LE_CONNECTION_COMPLETE_EVENT -> event = buildLeConnectionCompleteEvent(data);
            case LE_LONG_TERM_KEY_REQUEST_EVENT -> event = buildLeLongTermKeyRequestEvent(data);
            default -> throw new UnsupportedOperationException(
                    String.format("LE Subevent code: %s",subeventCode));
        }
        return event;
    }

    private PeriodicInquiryModeComplete buildPeriodicInquiryModeComplete(BinaryMessage data)
    {
        PeriodicInquiryModeComplete event = new PeriodicInquiryModeComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private ExitPeriodicInquiryModeComplete buildExitPeriodicInquiryModeComplete(BinaryMessage data)
    {
        ExitPeriodicInquiryModeComplete event = new ExitPeriodicInquiryModeComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private LeSetAdvertiseEnableComplete buildLeSetAdvertiseEnable(BinaryMessage data)
    {
        LeSetAdvertiseEnableComplete event = new LeSetAdvertiseEnableComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private LeSetAdvertisingParametersComplete buildLeSetAdvertisingParameters(BinaryMessage data)
    {
        LeSetAdvertisingParametersComplete event = new LeSetAdvertisingParametersComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private LeSetAdvertisingDataComplete buildLeSetAdvertisingData(BinaryMessage data)
    {
        LeSetAdvertisingDataComplete event = new LeSetAdvertisingDataComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private LeConnectionCompleteEvent buildLeConnectionCompleteEvent(BinaryMessage data)
    {
        LeConnectionCompleteEvent event = new LeConnectionCompleteEvent();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setConnectionHandle(data.read2octets());
        event.setRole(data.read1octet());
        event.setPeerAddressType(data.read1octet());
        event.setPeerAddress(new BluetoothAddress(data));
        event.setConnInterval(data.read2octets());
        event.setConnLatency(data.read2octets());
        event.setSupervisionTimeout(data.read2octets());
        event.setMasterClockAccuracy(data.read1octet());
        return event;
    }

    private LeLongTermKeyRequestEvent buildLeLongTermKeyRequestEvent(BinaryMessage data)
    {
        LeLongTermKeyRequestEvent event = new LeLongTermKeyRequestEvent();
        event.setConnectionHandle(data.read2octets());
        event.setRandomNumber(new EightByteValue(data));
        event.setEncryptedDiversifier(data.read2octets());
        return event;
    }
}
