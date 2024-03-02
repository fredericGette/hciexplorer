package fr.gette.hciexplorer.hciSpecification.event;

import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventAuthenticationComplete extends Event {
    private EventCode code = EventCode.AUTHENTICATION_COMPLETE;
    private ErrorCode status;
    private int connectionHandle;
}
