package fr.gette.hciexplorer.hciSpecification.event;

import fr.gette.hciexplorer.hciSpecification.BluetoothAddress;
import fr.gette.hciexplorer.hciSpecification.ClassOfDevice;
import fr.gette.hciexplorer.hciSpecification.PageScanRepetitionMode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EventInquiryResult extends Event {
    private EventCode code = EventCode.INQUIRY_RESULT;
    private short numResponses;
    private List<SingleInquiryResult> inquiryResult;
    @Getter
    @Setter
    public static class SingleInquiryResult
    {
        private BluetoothAddress bdAddr;
        private PageScanRepetitionMode pageScanRepetitionMode;
        private ClassOfDevice classOfDevice;
        private int clockOffset;
    }
}
