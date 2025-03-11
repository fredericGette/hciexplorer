package fr.gette.hciexplorer.hciSpecification.event.leMetaEvent;

import fr.gette.hciexplorer.hciSpecification.BluetoothAddress;
import fr.gette.hciexplorer.hciSpecification.EightByteValue;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import fr.gette.hciexplorer.hciSpecification.event.Event;
import fr.gette.hciexplorer.hciSpecification.event.EventCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LeLongTermKeyRequestEvent extends Event {
    private EventCode code = EventCode.LE_META_EVENT;
    private LeSubeventCode subeventCode = LeSubeventCode.LE_LONG_TERM_KEY_REQUEST_EVENT;
    private int connectionHandle;
    private EightByteValue randomNumber;
    private int encryptedDiversifier;

    public String getEncryptedDiversifier()
    {
        return String.format("%02X", encryptedDiversifier);
    }
}
