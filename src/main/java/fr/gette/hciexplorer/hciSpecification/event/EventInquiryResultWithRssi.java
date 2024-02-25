package fr.gette.hciexplorer.hciSpecification.event;

import fr.gette.hciexplorer.hciSpecification.BluetoothAddress;
import fr.gette.hciexplorer.hciSpecification.ClassOfDevice;
import fr.gette.hciexplorer.hciSpecification.ExtendedInquiryResponse;
import fr.gette.hciexplorer.hciSpecification.PageScanRepetitionMode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EventInquiryResultWithRssi extends Event {
    private EventCode code = EventCode.INQUIRY_RESULT_WITH_RSSI;
    private short numResponses;
    private List<SingleInquiryResultWithRssi> inquiryResultWithRssi;
    @Getter
    @Setter
    public static class SingleInquiryResultWithRssi
    {
        private BluetoothAddress bdAddr;
        private PageScanRepetitionMode pageScanRepetitionMode;
        private ClassOfDevice classOfDevice;
        private int clockOffset;
        private short rssi;
    }
}
