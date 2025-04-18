package fr.gette.hciexplorer.hciSpecification.data.l2cap.signaling;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConnectionParameterUpdateRequest extends SignalingPacket {
    private SignalingCommandCode commandCode = SignalingCommandCode.CONNECTION_PARAMETER_UPDATE_REQUEST;
    private int intervalMin;
    private int intervalMax;
    private int slaveLatency;
    private int timeoutMultiplier;

    public double getConnectionIntervalMinInMs()
    {
        return intervalMin * 1.25;
    }

    public double getConnectionIntervalMaxInMs()
    {
        return intervalMax * 1.25;
    }

    public double getConnectionSupervisionTimeoutInMs()
    {
        return timeoutMultiplier * 10;
    }
}
