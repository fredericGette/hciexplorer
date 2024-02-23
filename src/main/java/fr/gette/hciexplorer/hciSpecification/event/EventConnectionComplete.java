package fr.gette.hciexplorer.hciSpecification.event;

import fr.gette.hciexplorer.hciSpecification.BluetoothAddress;
import fr.gette.hciexplorer.hciSpecification.EncryptionEnabled;
import fr.gette.hciexplorer.hciSpecification.LinkType;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventConnectionComplete extends Event {
    private EventCode code = EventCode.CONNECTION_COMPLETE;
    private ErrorCode status;
    private int connectionHandle;
    private BluetoothAddress bdAddr;
    private LinkType          linkType;
    private EncryptionEnabled encryptionEnabled;
}
