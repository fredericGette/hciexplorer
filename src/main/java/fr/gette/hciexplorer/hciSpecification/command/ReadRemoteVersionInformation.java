package fr.gette.hciexplorer.hciSpecification.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReadRemoteVersionInformation extends Command {
    private CommandCode opCode = CommandCode.READ_REMOTE_VERSION_INFORMATION;
    private int connectionHandle;
}
