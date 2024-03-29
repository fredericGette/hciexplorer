package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.SimplePairingMode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class WriteSimplePairingMode extends Command {
    private CommandCode opCode = CommandCode.WRITE_SIMPLE_PAIRING_MODE;
    private SimplePairingMode simplePairingMode;

}
