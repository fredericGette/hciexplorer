package fr.gette.hciexplorer.hciSpecification.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Disconnect extends Command {
    private CommandCode opCode = CommandCode.DISCONNECT;
    private int connectionHandle;
    private ErrorCode Reason;
}
