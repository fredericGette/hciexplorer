package fr.gette.hciexplorer.hciSpecification.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReadLocalVersionInformation extends Command {
    private CommandCode opCode = CommandCode.READ_LOCAL_VERSION_INFORMATION;
}
