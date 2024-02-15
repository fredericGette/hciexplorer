package fr.gette.hciexplorer.service;

import fr.gette.hciexplorer.entity.*;
import fr.gette.hciexplorer.hciSpecification.HciMessage;
import fr.gette.hciexplorer.hciSpecification.HciPacketType;
import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlMessage;
import fr.gette.hciexplorer.repository.BeginReadRawMessageRepository;
import fr.gette.hciexplorer.repository.BeginWriteRawMessageRepository;
import fr.gette.hciexplorer.repository.EndReadRawMessageRepository;
import fr.gette.hciexplorer.repository.EndWriteRawMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
class MessageDecoder {

    private final BeginReadRawMessageRepository beginReadRawMessageRepository;
    private final BeginWriteRawMessageRepository beginWriteRawMessageRepository;
    private final EndReadRawMessageRepository endReadRawMessageRepository;
    private final EndWriteRawMessageRepository endWriteRawMessageRepository;
    private final EventDecoder eventDecoder;
    private final DataDecoder dataDecoder;
    private final CommandDecoder commandDecoder;

    HciMessage decodeReadMessage(Long id) {
        BeginReadRawMessage begin = beginReadRawMessageRepository.findById(id).orElseThrow(()->new NoSuchElementException(String.format("Missing %d", id)));
        EndReadRawMessage end = endReadRawMessageRepository.findById(id).orElseGet(()->new MissingEndReadRawMessage(id));
        return decode(begin, end);
    }

    HciMessage decodeWriteMessage(Long id) {
        BeginWriteRawMessage begin = beginWriteRawMessageRepository.findById(id).orElseThrow(NoSuchElementException::new);
        EndWriteRawMessage end = endWriteRawMessageRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return decode(begin, end);
    }


    private HciMessage decode(BeginReadRawMessage begin, EndReadRawMessage end)
    {
        HciMessage hciMsg;

        IoCtlMessage beginData = new IoCtlMessage(begin.getInputBuffer());
        // The message contains only one information: the type of the HCI packet.
        HciPacketType hciPacketTypeBegin = HciPacketType.get(beginData.readULong());

        switch (hciPacketTypeBegin)
        {
            case AclData -> hciMsg = dataDecoder.decode(begin, end);
            case Event -> hciMsg = eventDecoder.decode(begin, end);
            default -> throw new UnsupportedOperationException(
                    String.format("HCI packet type: %s",hciPacketTypeBegin));
        }

        hciMsg.setBeginTimestamp(begin.getTimestamp());
        hciMsg.setEndTimestamp(end.getTimestamp());

        return hciMsg;
    }

    private HciMessage decode(BeginWriteRawMessage begin, EndWriteRawMessage end)
    {
        HciMessage hciMsg;

        IoCtlMessage beginData = new IoCtlMessage(begin.getInputBuffer());
        // The message contains only one information: the type of the HCI packet.
        HciPacketType hciPacketTypeBegin = HciPacketType.get(beginData.readULong());

        switch (hciPacketTypeBegin)
        {
            case Command -> hciMsg = commandDecoder.decode(begin, end);
            case AclData -> hciMsg = dataDecoder.decode(begin, end);
            default -> throw new UnsupportedOperationException(
                    String.format("HCI packet type: %s",hciPacketTypeBegin));
        }

        hciMsg.setBeginTimestamp(begin.getTimestamp());
        hciMsg.setEndTimestamp(end.getTimestamp());

        return hciMsg;
    }

    private MissingEndReadRawMessage buildMissingEndReadRawMessage(Long id)
    {
        MissingEndReadRawMessage end = new MissingEndReadRawMessage(id);
        end.setTimestamp(endReadRawMessageRepository.findTopByOrderByTimestampDesc());
        return end;
    }

}
