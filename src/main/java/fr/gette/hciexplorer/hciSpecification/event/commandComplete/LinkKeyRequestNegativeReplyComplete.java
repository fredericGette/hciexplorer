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
public class LinkKeyRequestNegativeReplyComplete extends EventCommandComplete {
    private CommandCode commandOpCode = CommandCode.LINK_KEY_REQUEST_NEGATIVE_REPLY;
    private ErrorCode status;
    private BluetoothAddress bdAddr;
}
