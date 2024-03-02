package fr.gette.hciexplorer.hciSpecification.event;

import fr.gette.hciexplorer.hciSpecification.BluetoothAddress;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventPinCodeRequest extends Event {
    private EventCode code = EventCode.PIN_CODE_REQUEST;
    private BluetoothAddress bdAddr;
}
