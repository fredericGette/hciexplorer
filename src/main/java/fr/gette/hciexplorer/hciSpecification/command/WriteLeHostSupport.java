package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.LeSupportedHost;
import fr.gette.hciexplorer.hciSpecification.SimplePairingMode;
import fr.gette.hciexplorer.hciSpecification.SimultaneousLeHost;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WriteLeHostSupport extends Command {
    private CommandCode opCode = CommandCode.WRITE_LE_HOST_SUPPORT;
    private LeSupportedHost leSupportedHost;
    private SimultaneousLeHost simultaneousLeHost;
}
