package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.EventMask;
import fr.gette.hciexplorer.hciSpecification.SimplePairingMode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SetEventMask extends Command {
    private CommandCode opCode = CommandCode.SET_EVENT_MASK;
    private EventMask eventMask;

}
