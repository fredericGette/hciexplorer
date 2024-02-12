package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.HciMessage;
import fr.gette.hciexplorer.hciSpecification.HciPacketType;
import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommandFailed extends HciMessage {
    private HciPacketType type = HciPacketType.Command;
    private IoCtlStatus ioCtlStatus;
}
