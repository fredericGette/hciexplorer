package fr.gette.hciexplorer.hciSpecification.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import fr.gette.hciexplorer.hciSpecification.SimplePairingMode;

@Getter
@Setter
@NoArgsConstructor
public class WriteSimplePairingDebugMode extends Command {
    private CommandCode opCode = CommandCode.WRITE_SIMPLE_PAIRING_DEBUG_MODE;
    private SimplePairingMode simplePairingMode;

}
