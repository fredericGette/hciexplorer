package fr.gette.hciexplorer.hciSpecification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventFailed extends HciMessage {
    private HciPacketType type = HciPacketType.Event;
    private long errorCode;
}
