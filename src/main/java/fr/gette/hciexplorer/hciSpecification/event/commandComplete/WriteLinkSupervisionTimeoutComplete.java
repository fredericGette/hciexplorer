package fr.gette.hciexplorer.hciSpecification.event.commandComplete;

import fr.gette.hciexplorer.hciSpecification.BluetoothAddress;
import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WriteLinkSupervisionTimeoutComplete extends EventCommandComplete {
    private CommandCode commandOpCode = CommandCode.WRITE_LINK_SUPERVISION_TIMEOUT;
    private ErrorCode status;
    private int handle;
}
