package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.LinkPolicySettings;
import fr.gette.hciexplorer.hciSpecification.SimplePairingMode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WriteLinkPolicySettings extends Command {
    private CommandCode opCode = CommandCode.WRITE_LINK_POLICY_SETTINGS;
    private int connectionHandle;
    private LinkPolicySettings linkPolicySettings;
}
