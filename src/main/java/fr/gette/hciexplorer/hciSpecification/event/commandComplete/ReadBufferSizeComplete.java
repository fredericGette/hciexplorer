package fr.gette.hciexplorer.hciSpecification.event.commandComplete;

import fr.gette.hciexplorer.hciSpecification.SupportedCommands;
import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReadBufferSizeComplete extends EventCommandComplete {
    private CommandCode commandOpCode = CommandCode.READ_BUFFER_SIZE;
    private ErrorCode status;
    private int aclDataPacketLength;
    private short synchronousDataPacketLength;
    private int totalNumACLDataPackets;
    private int totalNumSynchronousDataPackets;
}
