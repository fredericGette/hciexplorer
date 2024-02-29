package fr.gette.hciexplorer.service;

import fr.gette.hciexplorer.entity.*;
import fr.gette.hciexplorer.hciSpecification.HciMessage;
import fr.gette.hciexplorer.hciSpecification.HciPacketType;
import fr.gette.hciexplorer.hciSpecification.UnknownWriteMessage;
import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlMessage;
import fr.gette.hciexplorer.repository.BeginReadRawMessageRepository;
import fr.gette.hciexplorer.repository.BeginWriteRawMessageRepository;
import fr.gette.hciexplorer.repository.EndReadRawMessageRepository;
import fr.gette.hciexplorer.repository.EndWriteRawMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

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
        EndReadRawMessage end = endReadRawMessageRepository.findById(id).orElseGet(()->buildMissingEndReadRawMessage(id));
        return decode(begin, end);
    }

    HciMessage decodeWriteMessage(Long id) {
        BeginWriteRawMessage begin = beginWriteRawMessageRepository.findById(id).orElseThrow(NoSuchElementException::new);
        EndWriteRawMessage end = endWriteRawMessageRepository.findById(id).orElseGet(()->buildMissingEndWriteRawMessage(id));
        return decode(begin, end);
    }


    private HciMessage decode(BeginReadRawMessage begin, EndReadRawMessage end)
    {
        HciMessage hciMsg;

        IoCtlMessage beginData = new IoCtlMessage(begin.getInputBuffer());
        // The message contains only one information: the type of the HCI packet.
        HciPacketType hciPacketTypeBegin = HciPacketType.get(beginData.read4octets());

        switch (hciPacketTypeBegin)
        {
            case ACLDATA -> hciMsg = dataDecoder.decode(begin, end);
            case EVENT -> hciMsg = eventDecoder.decode(begin, end);
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
        Long dataLength = beginData.read4octets();
        HciPacketType hciPacketType = HciPacketType.get(beginData.read1octet());

        if (hciPacketType != null) {
            switch (hciPacketType) {
                case COMMAND -> hciMsg = commandDecoder.decode(begin, end);
                case ACLDATA -> hciMsg = dataDecoder.decode(begin, end);
                default -> throw new UnsupportedOperationException(
                        String.format("HCI packet type: %s", hciPacketType));
            }
        }
        else
        {
            hciMsg = new UnknownWriteMessage();
        }

        hciMsg.setBeginTimestamp(begin.getTimestamp());
        hciMsg.setEndTimestamp(end.getTimestamp());

        return hciMsg;
    }

    private MissingEndReadRawMessage buildMissingEndReadRawMessage(Long id)
    {
        MissingEndReadRawMessage end = new MissingEndReadRawMessage(id);
        end.setTimestamp(findMaxTime());
        return end;
    }

    private MissingEndWriteRawMessage buildMissingEndWriteRawMessage(Long id)
    {
        MissingEndWriteRawMessage end = new MissingEndWriteRawMessage(id);
        end.setTimestamp(findMaxTime());
        return end;
    }

    private ZonedDateTime findMaxTime()
    {
        List<ZonedDateTime> lastTimes = new ArrayList<>();
        ZonedDateTime time0 = ZonedDateTime.of(0,1,1,0,0,0,0, ZoneId.systemDefault());
        lastTimes.add(Optional.ofNullable(beginReadRawMessageRepository.findFirstByOrderByTimestampDesc()).map(message -> message.getTimestamp()).orElseGet(()->time0));
        lastTimes.add(Optional.ofNullable(beginWriteRawMessageRepository.findFirstByOrderByTimestampDesc()).map(message -> message.getTimestamp()).orElseGet(()->time0));
        lastTimes.add(Optional.ofNullable(endReadRawMessageRepository.findFirstByOrderByTimestampDesc()).map(message -> message.getTimestamp()).orElseGet(()->time0));
        lastTimes.add(Optional.ofNullable(endWriteRawMessageRepository.findFirstByOrderByTimestampDesc()).map(message -> message.getTimestamp()).orElseGet(()->time0));

        return Collections.max(lastTimes);
    }

}
