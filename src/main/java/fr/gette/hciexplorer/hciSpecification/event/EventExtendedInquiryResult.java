package fr.gette.hciexplorer.hciSpecification.event;

import fr.gette.hciexplorer.hciSpecification.*;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventExtendedInquiryResult extends Event {
    private EventCode code = EventCode.EXTENDED_INQUIRY_RESULT;
    private short numResponses;
    private BluetoothAddress bdAddr;
    private PageScanRepetitionMode pageScanRepetitionMode;
    private ClassOfDevice classOfDevice;
    private int clockOffset;
    private short rssi;
    private ExtendedInquiryResponse extendedInquiryResponse;
}
