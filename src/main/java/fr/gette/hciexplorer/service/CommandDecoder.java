package fr.gette.hciexplorer.service;

import fr.gette.hciexplorer.entity.BeginWriteRawMessage;
import fr.gette.hciexplorer.entity.EndWriteRawMessage;
import fr.gette.hciexplorer.hciSpecification.ClassOfDevice;
import fr.gette.hciexplorer.hciSpecification.HciMessage;
import fr.gette.hciexplorer.hciSpecification.command.*;
import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlMessage;
import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlStatus;
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
        beginData.read4octets(); // remove the data length
        beginData.read1octet(); // remove the packet type

        IoCtlMessage endData = new IoCtlMessage(end.getOutputBuffer());
        IoCtlStatus ioCtlStatus = IoCtlStatus.get(end.getStatus());

        switch (ioCtlStatus)
        {
            case STATUS_SUCCESS -> {
                CommandCode opCode = CommandCode.get(beginData.read2octets());
                short payloadLength = beginData.read1octet(); // Size of the HCI_Command_Payload

                hciMsg = build(opCode, beginData);
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

    private Command build(CommandCode opCode, IoCtlMessage data)
    {
        Command command;
        switch(opCode)
        {
            case WRITE_CLASS_OF_DEVICE -> command = buildWriteClassOfDevice(data);
            case RESET -> command = buildReset(data);
            case READ_BD_ADDR -> command = buildReadBdAddr(data);
            case READ_LOCAL_SUPPORTED_COMMANDS -> command = buildReadLocalSupportedCommands(data);
            case READ_BUFFER_SIZE -> command = buildReadBufferSize(data);
            case READ_LOCAL_VERSION_INFORMATION -> command = buildReadLocalVersionInformation(data);
            case READ_LOCAL_SUPPORTED_FEATURES -> command = buildReadLocalSupportedFeature(data);
            case WRITE_SIMPLE_PAIRING_MODE -> command = buildWriteSimplePairingMode(data);
            case READ_LOCAL_OOB_DATA -> command = buildReadLocalOobData(data);
            default -> throw new UnsupportedOperationException(
                    String.format("OpCode : %s",opCode));
        }
        return command;
    }

    private Command buildReadLocalOobData(IoCtlMessage data) {
        return new ReadLocalOobData();
    }

    private Command buildWriteSimplePairingMode(IoCtlMessage data) {
        WriteSimplePairingMode command = new WriteSimplePairingMode();
        command.setSimplePairingMode(WriteSimplePairingMode.SimplePairingMode.get(data.read1octet()));
        return command;
    }

    private Command buildReadLocalSupportedFeature(IoCtlMessage data) {
        return new ReadLocalSupportedFeature();
    }

    private Command buildReadLocalVersionInformation(IoCtlMessage data) {
        return new ReadLocalVersionInformation();
    }

    private Command buildReadBufferSize(IoCtlMessage data) {
        return new ReadBufferSize();
    }

    private Command buildReadLocalSupportedCommands(IoCtlMessage data) {
        return new ReadLocalSupportedCommands();
    }

    private Command buildReadBdAddr(IoCtlMessage data) {
        return new ReadBdAddr();
    }

    private Command buildReset(IoCtlMessage data) {
        return new Reset();
    }

    private Command buildWriteClassOfDevice(IoCtlMessage data) {
        WriteClassOfDevice command = new WriteClassOfDevice();
        command.setClassOfDevice(new ClassOfDevice(data));
        return command;
    }
}
