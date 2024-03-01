package fr.gette.hciexplorer.hciSpecification.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleDiscovery extends Command {
    private CommandCode opCode = CommandCode.ROLE_DISCOVERY;
    private int connectionHandle;
}
