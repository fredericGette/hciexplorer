package fr.gette.hciexplorer.service;

import fr.gette.hciexplorer.entity.BeginReadRawMessage;
import fr.gette.hciexplorer.hciSpecification.HciMessage;
import fr.gette.hciexplorer.hciSpecification.event.Event;
import fr.gette.hciexplorer.repository.BeginReadRawMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService
{
    private final MessageDecoder messageDecoder;
    private final BeginReadRawMessageRepository beginReadRawMessageRepository;

    public HciMessage getReadMessage(Long id) {
        return messageDecoder.decodeReadMessage(id);
    }

    public HciMessage getWriteMessage(Long id) {
        return messageDecoder.decodeWriteMessage(id);
    }

    public List<Event> getEventMessages() {
        List<BeginReadRawMessage> beginReadRawMessages = beginReadRawMessageRepository.findByOrderByTimestampAsc();
        List<HciMessage> hciMessages = beginReadRawMessages.stream().map(m -> messageDecoder.decodeReadMessage(m.getId())).collect(Collectors.toList());
        return hciMessages.stream().filter(Event.class::isInstance).map(Event.class::cast).collect(Collectors.toList());
    }
}
