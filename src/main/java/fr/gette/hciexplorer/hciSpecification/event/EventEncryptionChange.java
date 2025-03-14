package fr.gette.hciexplorer.hciSpecification.event;

import fr.gette.hciexplorer.hciSpecification.EncryptionEnabled;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventEncryptionChange extends Event {
    private EventCode code = EventCode.ENCRYPTION_CHANGE;
    private ErrorCode status;
    private int connectionHandle;
    private EncryptionEnabled encryptionEnabled;
}
