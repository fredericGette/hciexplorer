package fr.gette.hciexplorer.service;

import fr.gette.hciexplorer.entity.BeginWriteRawMessage;
import fr.gette.hciexplorer.entity.EndWriteRawMessage;
import fr.gette.hciexplorer.hciSpecification.*;
import fr.gette.hciexplorer.hciSpecification.command.*;
import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlMessage;
import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommandDecoder {

    public HciMessage decode(String data)
    {
        IoCtlMessage beginData = new IoCtlMessage(data);

        return decode(beginData, IoCtlStatus.STATUS_SUCCESS);
    }

    public HciMessage decode(BeginWriteRawMessage begin, EndWriteRawMessage end) {
        HciMessage hciMsg;

        IoCtlMessage beginData = new IoCtlMessage(begin.getInputBuffer());

        IoCtlMessage endData = new IoCtlMessage(end.getOutputBuffer());
        IoCtlStatus ioCtlStatus = IoCtlStatus.get(end.getStatus());

        return decode(beginData, ioCtlStatus);
    }

    private HciMessage decode(IoCtlMessage beginData, IoCtlStatus ioCtlStatus) {
        HciMessage hciMsg;

        beginData.read4octets(); // remove the data length
        HciPacketType hciPacketType = HciPacketType.get(beginData.read1octet()); // remove the packet type
        if (!HciPacketType.COMMAND.equals(hciPacketType))
        {
            throw new IllegalArgumentException(String.format(
                    "Expected packet type 'COMMAND' (0x01), found %s",hciPacketType));
        }

        CommandCode opCode = CommandCode.get(beginData.read2octets());
        short payloadLength = beginData.read1octet(); // Size of the HCI_Command_Payload

        switch (ioCtlStatus)
        {
            case STATUS_SUCCESS -> {
                hciMsg = build(opCode, beginData);
            }
            case STATUS_CANCELLED -> {
                CommandCanceled commandCanceled = new CommandCanceled();
                commandCanceled.setOpCode(opCode);
                commandCanceled.setIoCtlStatus(ioCtlStatus);
                hciMsg = commandCanceled;
            }
            case STATUS_UNFINISHED -> {
                CommandUnfinished commandUnfinished = new CommandUnfinished();
                commandUnfinished.setOpCode(opCode);
                commandUnfinished.setIoCtlStatus(ioCtlStatus);
                hciMsg = commandUnfinished;
            }
            default -> throw new UnsupportedOperationException(
                    String.format("Status : %s",ioCtlStatus));
        }

        return hciMsg;
    }

    private Command build(CommandCode opCode, IoCtlMessage data)
    {
        Command command;
        switch(opCode)
        {
            case WRITE_CLASS_OF_DEVICE -> command = buildWriteClassOfDevice(data);
            case RESET -> command = buildReset(data);
            case READ_BD_ADDR -> command = buildReadBdAddr(data);
            case READ_LOCAL_SUPPORTED_COMMANDS -> command = buildReadLocalSupportedCommands(data);
            case READ_BUFFER_SIZE -> command = buildReadBufferSize(data);
            case READ_LOCAL_VERSION_INFORMATION -> command = buildReadLocalVersionInformation(data);
            case READ_LOCAL_SUPPORTED_FEATURES -> command = buildReadLocalSupportedFeature(data);
            case WRITE_SIMPLE_PAIRING_MODE -> command = buildWriteSimplePairingMode(data);
            case READ_LOCAL_OOB_DATA -> command = buildReadLocalOobData(data);
            case WRITE_SIMPLE_PAIRING_DEBUG_MODE -> command = buildWriteSimplePairingDebugMode(data);
            case WRITE_AUTHENTICATION_ENABLE -> command = buildWriteAuthenticationEnable(data);
            case SET_EVENT_MASK -> command = buildSetEventMask(data);
            case READ_INQUIRY_RESPONSE_TRANSMIT_POWER_LEVEL -> command = buildReadInquiryResponseTransmitPowerLevel(data);
            case WRITE_PAGE_TIMEOUT -> command = buildWritePageTimeout(data);
            case WRITE_PAGE_SCAN_ACTIVITY -> command = buildWritePageScanActivity(data);
            case WRITE_PAGE_SCAN_TYPE -> command = buildWritePageScanType(data);
            case WRITE_INQUIRY_SCAN_ACTIVITY -> command = buildWriteInquiryScanActivity(data);
            case WRITE_INQUIRY_SCAN_TYPE -> command = buildWriteInquiryScanType(data);
            case WRITE_INQUIRY_MODE -> command = buildWriteInquiryMode(data);
            case WRITE_EXTENDED_INQUIRY_RESPONSE -> command = buildWriteExtendedInquiryResponse(data);
            case HOST_BUFFER_SIZE -> command = buildHostBufferSize(data);
            case WRITE_LOCAL_NAME -> command = buildWriteLocalName(data);
            case LE_READ_LOCAL_SUPPORTED_FEATURES -> command = buildLeReadLocalSupportedFeatures(data);
            case LE_READ_SUPPORTED_STATES -> command = buildLeReadSupportedStates(data);
            case LE_READ_BUFFER_SIZE_V1 -> command = buildLeReadBufferSizeV1(data);
            case LE_READ_CONNECT_LIST_SIZE -> command = buildLeReadConnectListSize(data);
            case WRITE_LE_HOST_SUPPORT -> command = buildWriteLeHostSupport(data);
            case LE_READ_ADVERTISING_PHYSICAL_CHANNEL_TX_POWER -> command = buildLeReadAdvertisingPhysicalChannelTxPower(data);
            case LE_SET_EVENT_MASK -> command = buildLeSetEventMask(data);
            case WRITE_SCAN_ENABLE -> command = buildWriteScanEnable(data);
            case CREATE_CONNECTION -> command = buildCreateConnection(data);
            case WRITE_INQUIRY_TRANSMIT_POWER_LEVEL -> command = buildWriteInquiryTransmitPowerLevel(data);
            case INQUIRY -> command = buildInquiry(data);
            case REMOTE_NAME_REQUEST -> command = buildRemoteNameRequest(data);
            case READ_REMOTE_SUPPORTED_FEATURES -> command = buildReadRemoteSupportedFeatures(data);
            case READ_RSSI -> command = buildReadRssi(data);
            case ROLE_DISCOVERY -> command = buildRoleDiscovery(data);
            case WRITE_AUTOMATIC_FLUSH_TIMEOUT -> command = buildWriteAutomaticFlushTimeout(data);
            case READ_REMOTE_VERSION_INFORMATION -> command = buildReadRemoteVersionInformation(data);
            case READ_CLOCK_OFFSET -> command = buildReadClockOffset(data);
            case READ_TRANSMIT_POWER_LEVEL -> command = buildReadTransmitPowerLevel(data);
            case READ_LINK_QUALITY -> command = buildReadLinkQuality(data);
            case READ_LINK_SUPERVISION_TIMEOUT -> command = buildReadLinkSupervisionTimeout(data);
            case WRITE_LINK_POLICY_SETTINGS -> command = buildWriteLinkPolicySettings(data);
            case DISCONNECT -> command = buildDisconnect(data);
            case INQUIRY_CANCEL -> command = buildInquiryCancel(data);
            case AUTHENTICATION_REQUESTED -> command = buildAuthenticationRequested(data);
            case LINK_KEY_REQUEST_NEGATIVE_REPLY -> command = buildLinkKeyRequestNegativeReply(data);
            case PIN_CODE_REQUEST_REPLY -> command = buildPinCodeRequestReply(data);
            case SET_CONNECTION_ENCRYPTION -> command = buildSetConnectionEncryption(data);
            default -> throw new UnsupportedOperationException(
                    String.format("OpCode : %s",opCode));
        }
        return command;
    }

    private Command buildReadLocalOobData(IoCtlMessage data) {
        return new ReadLocalOobData();
    }

    private Command buildWriteSimplePairingMode(IoCtlMessage data) {
        WriteSimplePairingMode command = new WriteSimplePairingMode();
        command.setSimplePairingMode(SimplePairingMode.get(data.read1octet()));
        return command;
    }

    private Command buildReadLocalSupportedFeature(IoCtlMessage data) {
        return new ReadLocalSupportedFeature();
    }

    private Command buildReadLocalVersionInformation(IoCtlMessage data) {
        return new ReadLocalVersionInformation();
    }

    private Command buildReadBufferSize(IoCtlMessage data) {
        return new ReadBufferSize();
    }

    private Command buildReadLocalSupportedCommands(IoCtlMessage data) {
        return new ReadLocalSupportedCommands();
    }

    private Command buildReadBdAddr(IoCtlMessage data) {
        return new ReadBdAddr();
    }

    private Command buildReset(IoCtlMessage data) {
        return new Reset();
    }

    private Command buildWriteClassOfDevice(IoCtlMessage data) {
        WriteClassOfDevice command = new WriteClassOfDevice();
        command.setClassOfDevice(new ClassOfDevice(data));
        return command;
    }

    private Command buildWriteSimplePairingDebugMode(IoCtlMessage data) {
        WriteSimplePairingDebugMode command = new WriteSimplePairingDebugMode();
        command.setSimplePairingMode(SimplePairingMode.get(data.read1octet()));
        return command;
    }

    private Command buildWriteAuthenticationEnable(IoCtlMessage data) {
        WriteAuthenticationEnable command = new WriteAuthenticationEnable();
        command.setAuthenticationEnable(AuthenticationEnable.get(data.read1octet()));
        return command;
    }

    private Command buildSetEventMask(IoCtlMessage data) {
        SetEventMask command = new SetEventMask();
        command.setEventMask(new EventMask(data));
        return command;
    }

    private Command buildReadInquiryResponseTransmitPowerLevel(IoCtlMessage data) {
        ReadInquiryResponseTransmitPowerLevel command = new ReadInquiryResponseTransmitPowerLevel();
        return command;
    }

    private Command buildWritePageTimeout(IoCtlMessage data) {
        WritePageTimeout command = new WritePageTimeout();
        command.setPageTimeout(data.read2octets());
        return command;
    }

    private Command buildWritePageScanActivity(IoCtlMessage data) {
        WritePageScanActivity command = new WritePageScanActivity();
        command.setPageScanInterval(data.read2octets());
        command.setPageScanWindow(data.read2octets());
        return command;
    }

    private Command buildWritePageScanType(IoCtlMessage data) {
        WritePageScanType command = new WritePageScanType();
        command.setPageScanType(PageScanType.get(data.read1octet()));
        return command;
    }

    private Command buildWriteInquiryScanActivity(IoCtlMessage data) {
        WriteInquiryScanActivity command = new WriteInquiryScanActivity();
        command.setInquiryScanInterval(data.read2octets());
        command.setInquiryScanWindow(data.read2octets());
        return command;
    }

    private Command buildWriteInquiryScanType(IoCtlMessage data) {
        WriteInquiryScanType command = new WriteInquiryScanType();
        command.setScanType(InquiryScanType.get(data.read1octet()));
        return command;
    }

    private Command buildWriteInquiryMode(IoCtlMessage data) {
        WriteInquiryMode command = new WriteInquiryMode();
        command.setInquiryMode(InquiryMode.get(data.read1octet()));
        return command;
    }

    private Command buildWriteExtendedInquiryResponse(IoCtlMessage data) {
        WriteExtendedInquiryResponse command = new WriteExtendedInquiryResponse();
        command.setFecRequired(FecRequired.get(data.read1octet()));
        command.setExtendedInquiryResponse(new ExtendedInquiryResponse(data));
        return command;
    }

    private Command buildHostBufferSize(IoCtlMessage data) {
        HostBufferSize command = new HostBufferSize();
        command.setHostACLDataPacketLength(data.read2octets());
        command.setHostSynchronousDataPacketLength(data.read1octet());
        command.setHostTotalNumACLDataPackets(data.read2octets());
        command.setHostTotalNumSynchronousDataPackets(data.read2octets());
        return command;
    }

    private Command buildWriteLocalName(IoCtlMessage data) {
        WriteLocalName command = new WriteLocalName();
        command.setLocalName(data.readNullTerminatedString(248));
        return command;
    }

    private Command buildLeReadLocalSupportedFeatures(IoCtlMessage data) {
        LeReadLocalSupportedFeatures command = new LeReadLocalSupportedFeatures();
        return command;
    }

    private Command buildLeReadSupportedStates(IoCtlMessage data) {
        LeReadSupportedStates command = new LeReadSupportedStates();
        return command;
    }

    private Command buildLeReadBufferSizeV1(IoCtlMessage data) {
        LeReadBufferSizeV1 command = new LeReadBufferSizeV1();
        return command;
    }

    private Command buildLeReadConnectListSize(IoCtlMessage data) {
        LeReadConnectListSize command = new LeReadConnectListSize();
        return command;
    }

    private Command buildWriteLeHostSupport(IoCtlMessage data) {
        WriteLeHostSupport command = new WriteLeHostSupport();
        command.setLeSupportedHost(LeSupportedHost.get(data.read1octet()));
        command.setSimultaneousLeHost(SimultaneousLeHost.get(data.read1octet()));
        return command;
    }

    private Command buildLeReadAdvertisingPhysicalChannelTxPower(IoCtlMessage data) {
        LeReadAdvertisingPhysicalChannelTxPower command = new LeReadAdvertisingPhysicalChannelTxPower();
        return command;
    }

    private Command buildLeSetEventMask(IoCtlMessage data) {
        LeSetEventMask command = new LeSetEventMask();
        command.setLeEventMask(new LeEventMask(data));
        return command;
    }

    private Command buildWriteScanEnable(IoCtlMessage data) {
        WriteScanEnable command = new WriteScanEnable();
        command.setScanEnable(new ScanEnable(data));
        return command;
    }

    private Command buildCreateConnection(IoCtlMessage data) {
        CreateConnection command = new CreateConnection();
        command.setBdAddr(new BluetoothAddress(data));
        command.setPacketType(new PacketTypeUsable(data));
        command.setPageScanRepetitionMode(PageScanRepetitionMode.get(data.read1octet()));
        command.setReserved(data.read1octet());
        command.setClockOffset(new ClockOffset(data));
        command.setAllowRoleSwitch(AllowRoleSwitch.get(data.read1octet()));
        return command;
    }

    private Command buildWriteInquiryTransmitPowerLevel(IoCtlMessage data) {
        WriteInquiryTransmitPowerLevel command = new WriteInquiryTransmitPowerLevel();
        command.setTxPower(data.read1signedOctet());
        return command;
    }

    private Command buildInquiry(IoCtlMessage data) {
        Inquiry command = new Inquiry();
        command.setLap(new LAP(data));
        command.setInquiryLength(data.read1octet());
        command.setNumResponses(data.read1octet());
        return command;
    }

    private Command buildRemoteNameRequest(IoCtlMessage data) {
        RemoteNameRequest command = new RemoteNameRequest();
        command.setBdAddr(new BluetoothAddress(data));
        command.setPageScanRepetitionMode(PageScanRepetitionMode.get(data.read1octet()));
        command.setReserved(data.read1octet());
        command.setClockOffset(new ClockOffset(data));
        return command;
    }

    private Command buildReadRemoteSupportedFeatures(IoCtlMessage data) {
        ReadRemoteSupportedFeatures command = new ReadRemoteSupportedFeatures();
        command.setConnectionHandle(data.read2octets());
        return command;
    }

    private Command buildReadRssi(IoCtlMessage data) {
        ReadRssi command = new ReadRssi();
        command.setHandle(data.read2octets());
        return command;
    }

    private Command buildRoleDiscovery(IoCtlMessage data) {
        RoleDiscovery command = new RoleDiscovery();
        command.setConnectionHandle(data.read2octets());
        return command;
    }

    private Command buildWriteAutomaticFlushTimeout(IoCtlMessage data) {
        WriteAutomaticFlushTimeout command = new WriteAutomaticFlushTimeout();
        command.setConnectionHandle(data.read2octets());
        command.setFlushTimeout(data.read2octets());
        return command;
    }

    private Command buildReadRemoteVersionInformation(IoCtlMessage data) {
        ReadRemoteVersionInformation command = new ReadRemoteVersionInformation();
        command.setConnectionHandle(data.read2octets());
        return command;
    }

    private Command buildReadClockOffset(IoCtlMessage data) {
        ReadClockOffset command = new ReadClockOffset();
        command.setConnectionHandle(data.read2octets());
        return command;
    }

    private Command buildReadTransmitPowerLevel(IoCtlMessage data) {
        ReadTransmitPowerLevel command = new ReadTransmitPowerLevel();
        command.setConnectionHandle(data.read2octets());
        command.setTransmitPowerLevelType(TransmitPowerLevelType.get(data.read1octet()));
        return command;
    }

    private Command buildReadLinkQuality(IoCtlMessage data) {
        ReadLinkQuality command = new ReadLinkQuality();
        command.setHandle(data.read2octets());
        return command;
    }

    private Command buildReadLinkSupervisionTimeout(IoCtlMessage data) {
        ReadLinkSupervisionTimeout command = new ReadLinkSupervisionTimeout();
        command.setConnectionHandle(data.read2octets());
        return command;
    }

    private Command buildWriteLinkPolicySettings(IoCtlMessage data) {
        WriteLinkPolicySettings command = new WriteLinkPolicySettings();
        command.setConnectionHandle(data.read2octets());
        command.setLinkPolicySettings(new LinkPolicySettings(data));
        return command;
    }

    private Command buildDisconnect(IoCtlMessage data) {
        Disconnect command = new Disconnect();
        command.setConnectionHandle(data.read2octets());
        command.setReason(ErrorCode.get(data.read1octet()));
        return command;
    }

    private Command buildInquiryCancel(IoCtlMessage data) {
        InquiryCancel command = new InquiryCancel();
        return command;
    }

    private Command buildAuthenticationRequested(IoCtlMessage data) {
        AuthenticationRequested command = new AuthenticationRequested();
        command.setConnectionHandle(data.read2octets());
        return command;
    }

    private Command buildLinkKeyRequestNegativeReply(IoCtlMessage data) {
        LinkKeyRequestNegativeReply command = new LinkKeyRequestNegativeReply();
        command.setBdAddr(new BluetoothAddress(data));
        return command;
    }

    private Command buildPinCodeRequestReply(IoCtlMessage data) {
        PinCodeRequestReply command = new PinCodeRequestReply();
        command.setBdAddr(new BluetoothAddress(data));
        command.setPinCodeLength(data.read1octet());
        command.setPinCode(data.readRemaining());
        return command;
    }

    private Command buildSetConnectionEncryption(IoCtlMessage data) {
        SetConnectionEncryption command = new SetConnectionEncryption();
        command.setConnectionHandle(data.read2octets());
        command.setEncryptionEnable(EncryptionEnabled.get(data.read1octet()));
        return command;
    }
}
