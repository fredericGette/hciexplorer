package fr.gette.hciexplorer.hciSpecification.event.commandComplete;

import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WriteClassOfDeviceComplete extends EventCommandComplete {
    private CommandCode commandOpCode = CommandCode.CONTROLLER_AND_BASEBAND_WRITE_CLASS_OF_DEVICE;
    private ErrorCode status;
}
