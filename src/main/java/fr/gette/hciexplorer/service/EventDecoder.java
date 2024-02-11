package fr.gette.hciexplorer.service;

import fr.gette.hciexplorer.entity.BeginReadRawMessage;
import fr.gette.hciexplorer.entity.EndRawMessage;
import fr.gette.hciexplorer.entity.EndReadRawMessage;
import fr.gette.hciexplorer.hciSpecification.*;
import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import fr.gette.hciexplorer.hciSpecification.event.Event;
import fr.gette.hciexplorer.hciSpecification.event.EventCode;
import fr.gette.hciexplorer.hciSpecification.event.commandComplete.EventCommandComplete;
import fr.gette.hciexplorer.hciSpecification.event.EventFailed;
import fr.gette.hciexplorer.hciSpecification.event.commandComplete.WriteClassOfDeviceComplete;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventDecoder {

    public HciMessage decode(BeginReadRawMessage begin, EndReadRawMessage end)
    {
        HciMessage hciMsg;

        IoMessage endData = new IoMessage(end.getOutputBuffer());

        if (EndRawMessage.STATUS_SUCCESS.equals(end.getStatus()))
        {
            long size = endData.readULong(); // size of the HCI packet
            HciPacketType hciPacketTypeEnd = HciPacketType.get(endData.readUChar());
            EventCode eventCode = EventCode.get(endData.readUChar());
            short payloadLength = endData.readUChar();

            hciMsg = build(eventCode, endData);
        }
        else
        {
            EventFailed eventFailed = new EventFailed();
            eventFailed.setErrorCode(end.getStatus());
            hciMsg = eventFailed;
        }

        return hciMsg;
    }

    private Event build(EventCode eventCode, IoMessage data)
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

    private EventCommandComplete buildCommandComplete(IoMessage data)
    {
        EventCommandComplete event;
        short numHciCommandPackets = data.readUChar();
        CommandCode commandOpcode = CommandCode.get(data.readUShort());
        switch(commandOpcode)
        {
            case CONTROLLER_AND_BASEBAND_WRITE_CLASS_OF_DEVICE -> event = buildWriteClassOfDeviceComplete(data);
            default -> throw new UnsupportedOperationException(
                    String.format("Command Opcode : %s",commandOpcode));
        }
        event.setNumHciCommandPackets(numHciCommandPackets);
        return event;
    }

    private WriteClassOfDeviceComplete buildWriteClassOfDeviceComplete(IoMessage data)
    {
        WriteClassOfDeviceComplete event = new WriteClassOfDeviceComplete();
        event.setStatus(ErrorCode.get(data.readUChar()));
        return event;
    }
}
