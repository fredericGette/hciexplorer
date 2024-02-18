package fr.gette.hciexplorer.hciSpecification.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReadBdAddr extends Command {
    private CommandCode opCode = CommandCode.READ_BD_ADDR;
}
