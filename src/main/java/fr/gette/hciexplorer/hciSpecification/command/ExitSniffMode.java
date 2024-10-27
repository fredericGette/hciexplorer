package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.EncryptionEnabled;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExitSniffMode extends Command {
    private CommandCode opCode = CommandCode.EXIT_SNIFF_MODE;
    private int connectionHandle;
}
