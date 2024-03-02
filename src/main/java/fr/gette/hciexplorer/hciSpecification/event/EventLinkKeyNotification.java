package fr.gette.hciexplorer.hciSpecification.event;

import fr.gette.hciexplorer.hciSpecification.BluetoothAddress;
import fr.gette.hciexplorer.hciSpecification.KeyType;
import fr.gette.hciexplorer.hciSpecification.LinkKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventLinkKeyNotification extends Event {
    private EventCode code = EventCode.LINK_KEY_NOTIFICATION;
    private BluetoothAddress bdAddr;
    private LinkKey linkKey;
    private KeyType keyType;
}
