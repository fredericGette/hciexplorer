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
public class PinCodeRequestReplyComplete extends EventCommandComplete {
    private CommandCode commandOpCode = CommandCode.PIN_CODE_REQUEST_REPLY;
    private ErrorCode status;
    private BluetoothAddress bdAddr;
}
