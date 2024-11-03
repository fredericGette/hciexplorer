package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.service.IoCtlStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommandCanceled extends Command {
    private CommandCode opCode;
    private IoCtlStatus ioCtlStatus;
}
