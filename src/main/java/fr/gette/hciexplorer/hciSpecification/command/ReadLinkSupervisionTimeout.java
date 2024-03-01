package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.TransmitPowerLevelType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReadLinkSupervisionTimeout extends Command {
    private CommandCode opCode = CommandCode.READ_LINK_SUPERVISION_TIMEOUT;
    private int connectionHandle;
}
