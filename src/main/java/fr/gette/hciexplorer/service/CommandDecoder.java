package fr.gette.hciexplorer.service;

import fr.gette.hciexplorer.entity.BeginWriteRawMessage;
import fr.gette.hciexplorer.entity.EndRawMessage;
import fr.gette.hciexplorer.entity.EndWriteRawMessage;
import fr.gette.hciexplorer.hciSpecification.HciMessage;
import fr.gette.hciexplorer.hciSpecification.HciPacketType;
import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlStatus;
import fr.gette.hciexplorer.hciSpecification.command.Command;
import fr.gette.hciexplorer.hciSpecification.command.CommandFailed;
import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
class CommandDecoder {

    HciMessage decode(BeginWriteRawMessage begin, EndWriteRawMessage end)
    {
        HciMessage hciMsg;

        IoCtlMessage beginData = new IoCtlMessage(begin.getInputBuffer());
        beginData.readULong(); // remove the type of the HCI packet.

        IoCtlMessage endData = new IoCtlMessage(end.getOutputBuffer());

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
            commandFailed.setIoCtlStatus(IoCtlStatus.get(end.getStatus()));
            hciMsg = commandFailed;
        }

        return hciMsg;
    }
}
