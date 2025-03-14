package fr.gette.hciexplorer.hciSpecification.event.leMetaEvent;

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
public class LeConnectionUpdateCompleteEvent extends Event {
    private EventCode code = EventCode.LE_META_EVENT;
    private LeSubeventCode subeventCode = LeSubeventCode.LE_CONNECTION_UPDATE_COMPLETE_EVENT;
    private ErrorCode status;
    private int connectionHandle;
    private int connInterval;
    private int connLatency;
    private int supervisionTimeout;

    public Double getConnIntervalInMs()
    {
        return connInterval*1.25;
    }
    public Double getConnLatencyInMs()
    {
        return connLatency*1.25;
    }
    public Double getSupervisionTimeoutInMs()
    {
        return supervisionTimeout*10.0;
    }
}
