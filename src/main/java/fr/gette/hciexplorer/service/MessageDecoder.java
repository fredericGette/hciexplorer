package fr.gette.hciexplorer.service;

import fr.gette.hciexplorer.entity.BeginReadRawMessage;
import fr.gette.hciexplorer.entity.EndReadRawMessage;
import fr.gette.hciexplorer.hciSpecification.HciMessage;
import fr.gette.hciexplorer.hciSpecification.HciPacketType;
import fr.gette.hciexplorer.repository.BeginReadRawMessageRepository;
import fr.gette.hciexplorer.repository.BeginWriteRawMessageRepository;
import fr.gette.hciexplorer.repository.EndReadRawMessageRepository;
import fr.gette.hciexplorer.repository.EndWriteRawMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import static fr.gette.hciexplorer.service.DecoderHelper.readULong;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageDecoder {

    private final BeginReadRawMessageRepository beginReadRawMessageRepository;
    private final BeginWriteRawMessageRepository beginWriteRawMessageRepository;
    private final EndReadRawMessageRepository endReadRawMessageRepository;
    private final EndWriteRawMessageRepository endWriteRawMessageRepository;
    private final EventDecoder eventDecoder;
    private final DataDecoder dataDecoder;
    private final CommandDecoder commandDecoder;

    public HciMessage decodeReadMessage(Long id) {
        BeginReadRawMessage begin = beginReadRawMessageRepository.findById(id).orElseThrow(NoSuchElementException::new);
        EndReadRawMessage end = endReadRawMessageRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return decode(begin, end);
    }


    public HciMessage decode(BeginReadRawMessage begin, EndReadRawMessage end)
    {
        HciMessage hciMsg;

        DecoderHelper.IoMessage beginData = new DecoderHelper.IoMessage(begin.getInputBuffer());
        // The message contains only one information: the type of the HCI packet.
        HciPacketType hciPacketTypeBegin = HciPacketType.get(readULong(beginData));

        switch (hciPacketTypeBegin)
        {
            case Command -> hciMsg = commandDecoder.decode(begin, end);
            case AclData -> hciMsg = dataDecoder.decode(begin, end);
            case Event -> hciMsg = eventDecoder.decode(begin, end);
            default -> throw new UnsupportedOperationException(
                    String.format("HCI packet type: {}",hciPacketTypeBegin));
        }

        return hciMsg;
    }

}
