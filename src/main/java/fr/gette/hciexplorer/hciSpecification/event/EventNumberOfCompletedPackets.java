package fr.gette.hciexplorer.hciSpecification.event;

import fr.gette.hciexplorer.hciSpecification.BluetoothAddress;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EventNumberOfCompletedPackets extends Event {
    private EventCode code = EventCode.NUMBER_OF_COMPLETED_PACKETS;
    private short numberOfHandles;
    private List<Integer> connectionHandle;
    private List<Integer> hcNumOfCompletedPackets;
}
