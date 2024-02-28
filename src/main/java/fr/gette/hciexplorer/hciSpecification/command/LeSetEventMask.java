package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.LeEventMask;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LeSetEventMask extends Command {
    private CommandCode opCode = CommandCode.LE_SET_EVENT_MASK;
    private LeEventMask leEventMask;
}
