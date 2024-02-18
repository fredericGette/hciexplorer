package fr.gette.hciexplorer.service;

import fr.gette.hciexplorer.entity.BeginReadRawMessage;
import fr.gette.hciexplorer.entity.EndReadRawMessage;
import fr.gette.hciexplorer.hciSpecification.*;
import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import fr.gette.hciexplorer.hciSpecification.event.Event;
import fr.gette.hciexplorer.hciSpecification.event.EventCode;
import fr.gette.hciexplorer.hciSpecification.event.EventUnfinished;
import fr.gette.hciexplorer.hciSpecification.event.commandComplete.*;
import fr.gette.hciexplorer.hciSpecification.event.EventCanceled;
import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlStatus;
import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
            default -> throw new UnsupportedOperationException(
                    String.format("Command Opcode : %s",commandOpcode));
        }
        event.setNumHciCommandPackets(numHciCommandPackets);
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
}
