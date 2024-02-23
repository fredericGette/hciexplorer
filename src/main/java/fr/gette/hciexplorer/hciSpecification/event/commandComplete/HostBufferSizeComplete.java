package fr.gette.hciexplorer.hciSpecification.event.commandComplete;

import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HostBufferSizeComplete extends EventCommandComplete {
    private CommandCode commandOpCode = CommandCode.HOST_BUFFER_SIZE;
    private ErrorCode status;
}
