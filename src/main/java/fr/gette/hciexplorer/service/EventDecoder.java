package fr.gette.hciexplorer.service;

import fr.gette.hciexplorer.entity.BeginReadRawMessage;
import fr.gette.hciexplorer.entity.EndReadRawMessage;
import fr.gette.hciexplorer.hciSpecification.*;
import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import fr.gette.hciexplorer.hciSpecification.event.*;
import fr.gette.hciexplorer.hciSpecification.event.commandComplete.*;
import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlStatus;
import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
class EventDecoder {

    HciMessage decode(BeginReadRawMessage begin, EndReadRawMessage end)
    {
        HciMessage hciMsg;

        IoCtlMessage endData = new IoCtlMessage(end.getOutputBuffer());
        IoCtlStatus ioCtlStatus = IoCtlStatus.get(end.getStatus());

        switch (ioCtlStatus)
        {
            case STATUS_SUCCESS -> {
                long size = endData.read4octets(); // size of the HCI packet
                HciPacketType hciPacketTypeEnd = HciPacketType.get(endData.read1octet());
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

    private Event build(EventCode eventCode, IoCtlMessage data)
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
            default -> throw new UnsupportedOperationException(
                    String.format("Event code: %s",eventCode));
        }

        return event;
    }

    private EventCommandComplete buildCommandComplete(IoCtlMessage data)
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
            default -> throw new UnsupportedOperationException(
                    String.format("Command Opcode : %s",commandOpcode));
        }
        event.setNumHciCommandPackets(numHciCommandPackets);
        return event;
    }

    private EventCommandStatus buildCommandStatus(IoCtlMessage data)
    {
        EventCommandStatus event = new EventCommandStatus();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setNumHciCommandPackets(data.read1octet());
        event.setCommandOpCode(CommandCode.get(data.read2octets()));
        return event;
    }

    private EventConnectionComplete buildConnectionComplete(IoCtlMessage data)
    {
        EventConnectionComplete event = new EventConnectionComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setConnectionHandle(data.read2octets());
        event.setBdAddr(new BluetoothAddress(data));
        event.setLinkType(LinkType.get(data.read1octet()));
        event.setEncryptionEnabled(EncryptionEnabled.get(data.read1octet()));
        return event;
    }

    private EventExtendedInquiryResult buildExtendedInquiryResult(IoCtlMessage data)
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

    private EventInquiryResultWithRssi buildInquiryResultWithRssi(IoCtlMessage data)
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

    private EventInquiryComplete buildInquiryComplete(IoCtlMessage data)
    {
        EventInquiryComplete event = new EventInquiryComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private EventRemoteNameRequestComplete buildRemoteNameRequestComplete(IoCtlMessage data)
    {
        EventRemoteNameRequestComplete event = new EventRemoteNameRequestComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setBdAddr(new BluetoothAddress(data));
        event.setRemoteName(data.readNullTerminatedString(248));
        return event;
    }

    private EventReadRemoteSupportedFeaturesComplete buildReadRemoteSupportedFeaturesComplete(IoCtlMessage data)
    {
        EventReadRemoteSupportedFeaturesComplete event = new EventReadRemoteSupportedFeaturesComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setConnectionHandle(data.read2octets());
        event.setLmpFeatures(new SupportedLmpFeatures(data));
        return event;
    }

    private EventNumberOfCompletedPackets buildNumberOfCompletedPackets(IoCtlMessage data)
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

    private WriteClassOfDeviceComplete buildWriteClassOfDeviceComplete(IoCtlMessage data)
    {
        WriteClassOfDeviceComplete event = new WriteClassOfDeviceComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private ResetComplete buildResetComplete(IoCtlMessage data)
    {
        ResetComplete event = new ResetComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private ReadBdAddrComplete buildReadBdAddrComplete(IoCtlMessage data)
    {
        ReadBdAddrComplete event = new ReadBdAddrComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setBdAddr(new BluetoothAddress(data));
        return event;
    }

    private ReadLocalSupportedCommandsComplete buildReadLocalSupportedCommandsComplete(IoCtlMessage data)
    {
        ReadLocalSupportedCommandsComplete event = new ReadLocalSupportedCommandsComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setSupportedCommands(new SupportedCommands(data));
        return event;
    }

    private ReadBufferSizeComplete buildReadBufferSizeComplete(IoCtlMessage data)
    {
        ReadBufferSizeComplete event = new ReadBufferSizeComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setAclDataPacketLength(data.read2octets());
        event.setSynchronousDataPacketLength(data.read1octet());
        event.setTotalNumACLDataPackets(data.read2octets());
        event.setTotalNumSynchronousDataPackets(data.read2octets());
        return event;
    }

    private ReadLocalVersionInformationComplete buildReadLocalVersionInformationComplete(IoCtlMessage data)
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

    private ReadLocalSupportedFeatureComplete buildReadLocalSupportedFeatureComplete(IoCtlMessage data)
    {
        ReadLocalSupportedFeatureComplete event = new ReadLocalSupportedFeatureComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setLmpFeatures(new SupportedLmpFeatures(data));
        return event;
    }

    private WriteSimplePairingModeComplete buildWriteSimplePairingModeComplete(IoCtlMessage data)
    {
        WriteSimplePairingModeComplete event = new WriteSimplePairingModeComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private ReadLocalOobDataComplete buildReadLocalOobDataComplete(IoCtlMessage data)
    {
        ReadLocalOobDataComplete event = new ReadLocalOobDataComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setHashC(data);
        event.setRandomizerR(data);
        return event;
    }

    private WriteSimplePairingDebugModeComplete buildWriteSimplePairingDebugModeComplete(IoCtlMessage data)
    {
        WriteSimplePairingDebugModeComplete event = new WriteSimplePairingDebugModeComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private WriteAuthenticationEnableComplete buildWriteAuthenticationEnableComplete(IoCtlMessage data)
    {
        WriteAuthenticationEnableComplete event = new WriteAuthenticationEnableComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private SetEventMaskComplete buildSetEventMaskComplete(IoCtlMessage data)
    {
        SetEventMaskComplete event = new SetEventMaskComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private ReadInquiryResponseTransmitPowerLevelComplete buildReadInquiryResponseTransmitPowerLevelComplete(IoCtlMessage data)
    {
        ReadInquiryResponseTransmitPowerLevelComplete event = new ReadInquiryResponseTransmitPowerLevelComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setTxPower(data.read1signedOctet());
        return event;
    }

    private WritePageTimeoutComplete buildWritePageTimeoutComplete(IoCtlMessage data)
    {
        WritePageTimeoutComplete event = new WritePageTimeoutComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private WritePageTimeoutComplete buildWritePageScanActivityComplete(IoCtlMessage data)
    {
        WritePageTimeoutComplete event = new WritePageTimeoutComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private WritePageScanTypeComplete buildWritePageScanTypeComplete(IoCtlMessage data)
    {
        WritePageScanTypeComplete event = new WritePageScanTypeComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private WriteInquiryScanActivityComplete buildWriteInquiryScanActivityComplete(IoCtlMessage data)
    {
        WriteInquiryScanActivityComplete event = new WriteInquiryScanActivityComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private WriteInquiryScanTypeComplete buildWriteInquiryScanTypeComplete(IoCtlMessage data)
    {
        WriteInquiryScanTypeComplete event = new WriteInquiryScanTypeComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private WriteInquiryModeComplete buildWriteInquiryModeComplete(IoCtlMessage data)
    {
        WriteInquiryModeComplete event = new WriteInquiryModeComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private WriteExtendedInquiryResponseComplete buildWriteExtendedInquiryResponseComplete(IoCtlMessage data)
    {
        WriteExtendedInquiryResponseComplete event = new WriteExtendedInquiryResponseComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private HostBufferSizeComplete buildHostBufferSizeComplete(IoCtlMessage data)
    {
        HostBufferSizeComplete event = new HostBufferSizeComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private WriteLocalNameComplete buildWriteLocalNameComplete(IoCtlMessage data)
    {
        WriteLocalNameComplete event = new WriteLocalNameComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private LeReadLocalSupportedFeaturesComplete buildLeReadLocalSupportedFeaturesComplete(IoCtlMessage data)
    {
        LeReadLocalSupportedFeaturesComplete event = new LeReadLocalSupportedFeaturesComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setLeFeatures(new SupportedLeFeatures(data));
        return event;
    }

    private LeReadSupportedStatesComplete buildLeReadSupportedStatesComplete(IoCtlMessage data)
    {
        LeReadSupportedStatesComplete event = new LeReadSupportedStatesComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setLeStates(new SupportedLeStates((data)));
        return event;
    }

    private LeReadBufferSizeV1Complete buildLeReadBufferSizeV1Complete(IoCtlMessage data)
    {
        LeReadBufferSizeV1Complete event = new LeReadBufferSizeV1Complete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setHcLeDataPacketLength(data.read2octets());
        event.setHcTotalNumLeDataPackets(data.read1octet());
        return event;
    }

    private LeReadConnectListSizeComplete buildLeReadConnectListSizeComplete(IoCtlMessage data)
    {
        LeReadConnectListSizeComplete event = new LeReadConnectListSizeComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setWhiteListSize(data.read1octet());
        return event;
    }

    private WriteLeHostSupportComplete buildWriteLeHostSupportComplete(IoCtlMessage data)
    {
        WriteLeHostSupportComplete event = new WriteLeHostSupportComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private LeReadAdvertisingPhysicalChannelTxPowerComplete buildLeReadAdvertisingPhysicalChannelTxPowerComplete(IoCtlMessage data)
    {
        LeReadAdvertisingPhysicalChannelTxPowerComplete event = new LeReadAdvertisingPhysicalChannelTxPowerComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setTransmitPowerLevel(data.read1signedOctet());
        return event;
    }

    private LeSetEventMaskComplete buildLeSetEventMaskComplete(IoCtlMessage data)
    {
        LeSetEventMaskComplete event = new LeSetEventMaskComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private WriteScanEnableComplete buildWriteScanEnableComplete(IoCtlMessage data)
    {
        WriteScanEnableComplete event = new WriteScanEnableComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private WriteInquiryTransmitPowerLevelComplete buildWriteInquiryTransmitPowerLevelComplete(IoCtlMessage data)
    {
        WriteInquiryTransmitPowerLevelComplete event = new WriteInquiryTransmitPowerLevelComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        return event;
    }

    private ReadRssiComplete buildReadRssiComplete(IoCtlMessage data)
    {
        ReadRssiComplete event = new ReadRssiComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setHandle(data.read2octets());
        event.setRssi(data.read1signedOctet());
        return event;
    }

    private RoleDiscoveryComplete buildRoleDiscoveryComplete(IoCtlMessage data)
    {
        RoleDiscoveryComplete event = new RoleDiscoveryComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setConnectionHandle(data.read2octets());
        event.setCurrentRole(CurrentRole.get(data.read1octet()));
        return event;
    }

    private WriteAutomaticFlushTimeoutComplete buildWriteAutomaticFlushTimeoutComplete(IoCtlMessage data)
    {
        WriteAutomaticFlushTimeoutComplete event = new WriteAutomaticFlushTimeoutComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setConnectionHandle(data.read2octets());
        return event;
    }

    private ReadTransmitPowerLevelComplete buildReadTransmitPowerLevelComplete(IoCtlMessage data)
    {
        ReadTransmitPowerLevelComplete event = new ReadTransmitPowerLevelComplete();
        event.setStatus(ErrorCode.get(data.read1octet()));
        event.setConnectionHandle(data.read2octets());
        event.setTransmitPowerLevel(data.read1signedOctet());
        return event;
    }
}
