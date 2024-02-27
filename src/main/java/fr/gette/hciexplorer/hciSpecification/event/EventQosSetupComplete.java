package fr.gette.hciexplorer.hciSpecification.event;

import fr.gette.hciexplorer.hciSpecification.ServiceType;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventQosSetupComplete extends Event {
    private EventCode code = EventCode.QOS_SETUP_COMPLETE;
    private ErrorCode status;
    private int connectionHandle;
    private short       flags;
    private ServiceType serviceType;
    private long tokenRate;
    private long peakBandwidth;
    private long Latency;
    private long delayVariation;
}
