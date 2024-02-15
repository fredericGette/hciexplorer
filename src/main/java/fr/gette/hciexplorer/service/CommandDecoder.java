package fr.gette.hciexplorer.service;

import fr.gette.hciexplorer.entity.BeginWriteRawMessage;
import fr.gette.hciexplorer.entity.EndWriteRawMessage;
import fr.gette.hciexplorer.hciSpecification.HciMessage;
import fr.gette.hciexplorer.hciSpecification.HciPacketType;
import fr.gette.hciexplorer.hciSpecification.command.CommandUnfinished;
import fr.gette.hciexplorer.hciSpecification.event.EventUnfinished;
import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlStatus;
import fr.gette.hciexplorer.hciSpecification.command.Command;
import fr.gette.hciexplorer.hciSpecification.command.CommandCanceled;
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
        IoCtlStatus ioCtlStatus = IoCtlStatus.get(end.getStatus());

        switch (ioCtlStatus)
        {
            case STATUS_SUCCESS -> {
                long size = endData.readULong(); // size of the HCI packet
                HciPacketType hciPacketTypeEnd = HciPacketType.get(endData.readUChar());

                int opCode = beginData.readUShort();
                short payloadLength = beginData.readUChar();

                Command command = new Command();

                hciMsg = command;
            }
            case STATUS_CANCELLED -> {
                CommandCanceled commandCanceled = new CommandCanceled();
                commandCanceled.setIoCtlStatus(ioCtlStatus);
                hciMsg = commandCanceled;
            }
            case STATUS_UNFINISHED -> {
                CommandUnfinished commandUnfinished = new CommandUnfinished();
                commandUnfinished.setIoCtlStatus(ioCtlStatus);
                hciMsg = commandUnfinished;
            }
            default -> throw new UnsupportedOperationException(
                    String.format("Status : %s",ioCtlStatus));
        }

        return hciMsg;
    }
}
