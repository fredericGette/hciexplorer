package fr.gette.hciexplorer.hciSpecification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Event extends HciMessage {
    HciPacketType type = HciPacketType.Event;
    short code;

}
