package fr.gette.hciexplorer.hciSpecification.event.commandComplete;

import fr.gette.hciexplorer.hciSpecification.SupportedLeStates;
import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LeReadSupportedStatesComplete extends EventCommandComplete {
    private CommandCode commandOpCode = CommandCode.LE_READ_SUPPORTED_STATES;
    private ErrorCode         status;
    private SupportedLeStates leStates;
}
