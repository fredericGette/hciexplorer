package fr.gette.hciexplorer.hciSpecification.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LeReadBufferSizeV1 extends Command {
    private CommandCode opCode = CommandCode.LE_READ_BUFFER_SIZE_V1;
}
