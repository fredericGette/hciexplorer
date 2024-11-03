package fr.gette.hciexplorer.hciSpecification.event.commandComplete;

import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExitPeriodicInquiryModeComplete extends EventCommandComplete {
    private CommandCode commandOpCode = CommandCode.EXIT_PERIODIC_INQUIRY_MODE;
    private ErrorCode status;
}
