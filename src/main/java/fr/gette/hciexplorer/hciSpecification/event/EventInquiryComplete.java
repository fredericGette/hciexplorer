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
public class EventInquiryComplete extends Event {
    private EventCode code = EventCode.INQUIRY_COMPLETE;
    private ErrorCode status;
}
