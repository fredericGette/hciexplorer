package fr.gette.hciexplorer.service;

import fr.gette.hciexplorer.consolided.Bloc;
import fr.gette.hciexplorer.consolided.CommandBloc;
import fr.gette.hciexplorer.consolided.DataBloc;
import fr.gette.hciexplorer.consolided.EventBloc;
import fr.gette.hciexplorer.entity.BeginReadRawMessage;
import fr.gette.hciexplorer.entity.BeginWriteRawMessage;
import fr.gette.hciexplorer.hciSpecification.AclDirection;
import fr.gette.hciexplorer.hciSpecification.HciMessage;
import fr.gette.hciexplorer.hciSpecification.command.Command;
import fr.gette.hciexplorer.hciSpecification.data.AclData;
import fr.gette.hciexplorer.hciSpecification.event.Event;
import fr.gette.hciexplorer.hciSpecification.event.EventCode;
import fr.gette.hciexplorer.hciSpecification.event.EventCommandStatus;
import fr.gette.hciexplorer.hciSpecification.event.commandComplete.EventCommandComplete;
import fr.gette.hciexplorer.repository.BeginReadRawMessageRepository;
import fr.gette.hciexplorer.repository.BeginWriteRawMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Profile("database")
public class MessageService {
    private final MessageDecoder messageDecoder;
    private final BeginReadRawMessageRepository beginReadRawMessageRepository;
    private final BeginWriteRawMessageRepository beginWriteRawMessageRepository;

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

    public List<Command> getCommandMessages() {
        List<BeginWriteRawMessage> beginWriteRawMessages = beginWriteRawMessageRepository.findByOrderByTimestampAsc();
        List<HciMessage> hciMessages = beginWriteRawMessages.stream().map(m -> messageDecoder.decodeWriteMessage(m.getId())).collect(Collectors.toList());
        return hciMessages.stream().filter(Command.class::isInstance).map(Command.class::cast).collect(Collectors.toList());
    }

    public List<HciMessage> getMessages() {
        List<BeginReadRawMessage> beginReadRawMessages = beginReadRawMessageRepository.findAll();
        List<HciMessage> readMessages = beginReadRawMessages.stream().map(m -> messageDecoder.decodeReadMessage(m.getId())).collect(Collectors.toList());

        List<BeginWriteRawMessage> beginWriteRawMessages = beginWriteRawMessageRepository.findByOrderByTimestampAsc();
        List<HciMessage> writeMessages = beginWriteRawMessages.stream().map(m -> messageDecoder.decodeWriteMessage(m.getId())).collect(Collectors.toList());

        List<HciMessage> messages = readMessages;
        messages.addAll(writeMessages);
        Collections.sort(messages, Comparator.comparing(HciMessage::getBeginTimestamp));

        return messages;
    }

    public List<Bloc> getConsolidedMessages() {
        List<Bloc> blocs = new ArrayList<>();
        List<HciMessage> messages = getMessages();
        for (HciMessage message : messages) {
            if (message instanceof Command command) {
                CommandBloc cmdBloc = new CommandBloc();
                cmdBloc.setCommand(command);
                cmdBloc.setTimestamp(command.getBeginTimestamp());

                EventCommandStatus evtCmdStatus = findEventCommandStatus(command, messages);
                cmdBloc.setStatus(evtCmdStatus);

                Event evtCmdComplete = findEventCommandComplete(command, messages);
                cmdBloc.setResult(evtCmdComplete);

                blocs.add(cmdBloc);
            }
        }

        for (HciMessage message : messages) {
            if (message.getParentId() != 0) {
                continue;
            }

            if (message instanceof Event event) {
                EventBloc evtBloc = new EventBloc();
                evtBloc.setEvent(event);
                evtBloc.setTimestamp(event.getEndTimestamp());
                blocs.add(evtBloc);
            } else if (message instanceof AclData data) {
                DataBloc dataBloc = new DataBloc();
                dataBloc.setData(data);
                switch (data.getDirection()) {
                    case HOST_TO_CONTROLLER -> dataBloc.setTimestamp(data.getBeginTimestamp());
                    case CONTROLLER_TO_HOST -> dataBloc.setTimestamp(data.getEndTimestamp());
                }

                blocs.add(dataBloc);
            }
        }

        blocs.sort(Comparator.comparing(Bloc::getTimestamp));

        return blocs;
    }

    private EventCommandStatus findEventCommandStatus(Command command, List<HciMessage> messages) {
        EventCommandStatus evtCmdStatus = null;

        for (HciMessage message : messages) {
            if (!(message instanceof EventCommandStatus)) {
                continue;
            }
            EventCommandStatus candidate = (EventCommandStatus) message;

            if (!command.getOpCode().equals(candidate.getCommandOpCode())) {
                continue;
            }

            if (candidate.getParentId() != 0) {
                continue;
            }

            if (command.getEndTimestamp().compareTo(candidate.getEndTimestamp()) > 0) {
                // candidate is too early
                continue;
            }

            evtCmdStatus = candidate;
            evtCmdStatus.setParentId(command.getId());
            break;
        }

        return evtCmdStatus;
    }

    private Event findEventCommandComplete(Command command, List<HciMessage> messages) {
        Event evtCmdComplete = null;

        EventCode eventCompleteCode = null;
        switch (command.getOpCode()) {
            case CREATE_CONNECTION -> eventCompleteCode = EventCode.CONNECTION_COMPLETE;
            case INQUIRY -> eventCompleteCode = EventCode.INQUIRY_COMPLETE;
            case REMOTE_NAME_REQUEST -> eventCompleteCode = EventCode.REMOTE_NAME_REQUEST_COMPLETE;
            case READ_REMOTE_SUPPORTED_FEATURES -> eventCompleteCode = EventCode.READ_REMOTE_SUPPORTED_FEATURES_COMPLETE;
            case READ_REMOTE_VERSION_INFORMATION -> eventCompleteCode = EventCode.READ_REMOTE_VERSION_INFORMATION_COMPLETE;
            case READ_CLOCK_OFFSET -> eventCompleteCode = EventCode.READ_CLOCK_OFFSET_COMPLETE;
            case DISCONNECT -> eventCompleteCode = EventCode.DISCONNECTION_COMPLETE;
            case AUTHENTICATION_REQUESTED -> eventCompleteCode = EventCode.AUTHENTICATION_COMPLETE;
        }

        if (eventCompleteCode == null) {
            for (HciMessage message : messages) {
                if (!(message instanceof EventCommandComplete)) {
                    continue;
                }
                EventCommandComplete candidate = (EventCommandComplete) message;

                if (!command.getOpCode().equals(candidate.getCommandOpCode())) {
                    continue;
                }

                if (candidate.getParentId() != 0) {
                    continue;
                }

                if (command.getEndTimestamp().compareTo(candidate.getEndTimestamp()) > 0) {
                    // candidate is too early
                    continue;
                }

                evtCmdComplete = candidate;
                evtCmdComplete.setParentId(command.getId());
                break;
            }
        } else {
            for (HciMessage message : messages) {
                if (!(message instanceof Event)) {
                    continue;
                }
                Event candidate = (Event) message;

                if (!eventCompleteCode.equals(candidate.getCode())) {
                    continue;
                }

                if (candidate.getParentId() != 0) {
                    continue;
                }

                if (command.getEndTimestamp().compareTo(candidate.getEndTimestamp()) > 0) {
                    // candidate is too early
                    continue;
                }

                evtCmdComplete = candidate;
                evtCmdComplete.setParentId(command.getId());
                break;
            }
        }

        return evtCmdComplete;
    }
}
