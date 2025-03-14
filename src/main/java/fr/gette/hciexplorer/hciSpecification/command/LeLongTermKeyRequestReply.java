package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.SixteenByteValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LeLongTermKeyRequestReply extends Command {
    private CommandCode opCode = CommandCode.LE_LONG_TERM_KEY_REQUEST_REPLY;
    private int connectionHandle;
    private SixteenByteValue longTermKey;

}
