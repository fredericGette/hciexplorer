package fr.gette.hciexplorer.service;

import fr.gette.hciexplorer.entity.BeginReadRawMessage;
import fr.gette.hciexplorer.entity.EndRawMessage;
import fr.gette.hciexplorer.entity.EndReadRawMessage;
import fr.gette.hciexplorer.hciSpecification.*;
import fr.gette.hciexplorer.hciSpecification.command.Command;
import fr.gette.hciexplorer.hciSpecification.command.CommandFailed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommandDecoder {

    public HciMessage decode(BeginReadRawMessage begin, EndReadRawMessage end)
    {
        HciMessage hciMsg;

        IoMessage beginData = new IoMessage(begin.getInputBuffer());
        beginData.readULong(); // remove the type of the HCI packet.

        IoMessage endData = new IoMessage(end.getOutputBuffer());

        if (EndRawMessage.STATUS_SUCCESS.equals(end.getStatus()))
        {
            long size = endData.readULong(); // size of the HCI packet
            HciPacketType hciPacketTypeEnd = HciPacketType.get(endData.readUChar());

            int opCode = beginData.readUShort();
            short payloadLength = beginData.readUChar();

            Command command = new Command();

            hciMsg = command;
        }
        else
        {
            CommandFailed commandFailed = new CommandFailed();
            commandFailed.setErrorCode(end.getStatus());
            hciMsg = commandFailed;
        }

        return hciMsg;
    }
}
