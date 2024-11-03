package fr.gette.hciexplorer.hciSpecification.event;

import fr.gette.hciexplorer.service.IoCtlStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventCanceled extends Event {
    private IoCtlStatus ioCtlStatus;

    @Override
    public EventCode getCode() {
        return null;
    }
}
