package fr.gette.hciexplorer.hciSpecification.event.commandComplete;

import fr.gette.hciexplorer.hciSpecification.CurrentRole;
import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleDiscoveryComplete extends EventCommandComplete {
    private CommandCode commandOpCode = CommandCode.ROLE_DISCOVERY;
    private ErrorCode status;
    private int connectionHandle;
    private CurrentRole currentRole;
}
