package fr.gette.hciexplorer.hciSpecification.event.commandComplete;

import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WriteLinkPolicySettingsComplete extends EventCommandComplete {
    private CommandCode commandOpCode = CommandCode.WRITE_LINK_POLICY_SETTINGS;
    private ErrorCode status;
    private int connectionHandle;
}
