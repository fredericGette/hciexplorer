package fr.gette.hciexplorer.hciSpecification.event;

import fr.gette.hciexplorer.hciSpecification.ServiceType;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventDisconnectionComplete extends Event {
    private EventCode code = EventCode.DISCONNECTION_COMPLETE;
    private ErrorCode status;
    private int connectionHandle;
    private ErrorCode reason;
}
