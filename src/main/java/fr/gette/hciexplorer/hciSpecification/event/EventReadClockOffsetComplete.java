package fr.gette.hciexplorer.hciSpecification.event;

import fr.gette.hciexplorer.hciSpecification.BluetoothCoreSpecification;
import fr.gette.hciexplorer.hciSpecification.CompanyIdentifiers;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventReadClockOffsetComplete extends Event {
    private EventCode code = EventCode.READ_CLOCK_OFFSET_COMPLETE;
    private ErrorCode status;
    private int connectionHandle;
    private int clockOffset;
}
