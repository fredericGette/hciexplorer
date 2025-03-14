package fr.gette.hciexplorer.hciSpecification.event.commandComplete;

import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LeLongTermKeyRequestReply extends EventCommandComplete {
    private CommandCode commandOpCode = CommandCode.LE_LONG_TERM_KEY_REQUEST_REPLY;
    private ErrorCode status;
    private int connectionHandle;
}
