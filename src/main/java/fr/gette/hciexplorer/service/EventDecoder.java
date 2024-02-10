package fr.gette.hciexplorer.service;

import fr.gette.hciexplorer.entity.BeginReadRawMessage;
import fr.gette.hciexplorer.entity.EndRawMessage;
import fr.gette.hciexplorer.entity.EndReadRawMessage;
import fr.gette.hciexplorer.hciSpecification.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static fr.gette.hciexplorer.service.DecoderHelper.readUChar;
import static fr.gette.hciexplorer.service.DecoderHelper.readULong;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventDecoder {

    public HciMessage decode(BeginReadRawMessage begin, EndReadRawMessage end)
    {
        HciMessage hciMsg;

        DecoderHelper.IoMessage endData = new DecoderHelper.IoMessage(end.getOutputBuffer());

        if (EndRawMessage.STATUS_SUCCESS.equals(end.getStatus()))
        {
            long size = readULong(endData); // size of the HCI packet
            HciPacketType hciPacketTypeEnd = HciPacketType.get(readUChar(endData));
            short eventCode = readUChar(endData);
            short payloadLength = readUChar(endData);

            Event event = build(eventCode);

            hciMsg = event;
        }
        else
        {
            EventFailed eventFailed = new EventFailed();
            eventFailed.setErrorCode(end.getStatus());
            hciMsg = eventFailed;
        }

        return hciMsg;
    }

    private Event build(short eventCode)
    {
        Event event;

        switch (eventCode)
        {
            case 0x01 -> event = new EventInquiryComplete();
            case 0x0E -> event = new EventCommandComplete();
            default -> throw new UnsupportedOperationException(
                    String.format("Event code: {}",eventCode));
        }

        return event;
    }
}
