package fr.gette.hciexplorer.hciSpecification.event;

import fr.gette.hciexplorer.hciSpecification.BluetoothAddress;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventRemoteNameRequestComplete extends Event {
    private EventCode code = EventCode.REMOTE_NAME_REQUEST_COMPLETE;
    private ErrorCode status;
    private BluetoothAddress bdAddr;
    private String remoteName;
}
