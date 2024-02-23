package fr.gette.hciexplorer.hciSpecification.event.commandComplete;

import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LeReadBufferSizeV1Complete extends EventCommandComplete {
    private CommandCode commandOpCode = CommandCode.LE_READ_BUFFER_SIZE_V1;
    private ErrorCode status;
    private int   hcLeDataPacketLength;
    private short hcTotalNumLeDataPackets;
}
