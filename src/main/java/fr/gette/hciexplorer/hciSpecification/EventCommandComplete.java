package fr.gette.hciexplorer.hciSpecification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventCommandComplete extends Event {
    private EventCode code = EventCode.COMMAND_COMPLETE;
}
