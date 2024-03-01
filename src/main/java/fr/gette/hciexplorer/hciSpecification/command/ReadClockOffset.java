package fr.gette.hciexplorer.hciSpecification.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReadClockOffset extends Command {
    private CommandCode opCode = CommandCode.READ_CLOCK_OFFSET;
    private int connectionHandle;
}
