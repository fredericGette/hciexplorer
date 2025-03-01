package fr.gette.hciexplorer.hciSpecification.event.leMetaEvent;

import fr.gette.hciexplorer.hciSpecification.BluetoothAddress;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import fr.gette.hciexplorer.hciSpecification.event.Event;
import fr.gette.hciexplorer.hciSpecification.event.EventCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LeConnectionCompleteEvent extends Event {
    private EventCode code = EventCode.LE_META_EVENT;
    private LeSubeventCode subeventCode = LeSubeventCode.LE_CONNECTION_COMPLETE_EVENT;
    private ErrorCode status;
    private int connectionHandle;
    private short role;
    private short peerAddressType;
    private BluetoothAddress peerAddress;
    private int connInterval;
    private int connLatency;
    private int supervisionTimeout;
    private short masterClockAccuracy;

    public String getRole() {
        String name;
        switch (role) {
            case 0x00 -> name = "Connection is master";
            case 0x01 -> name = "Connection is slave";
            default -> name = "Reserved for future use";
        }
        return String.format("%s (0x%02X)", name, role);
    }

    public String getPeerAddressType() {
        String name;
        switch (peerAddressType) {
            case 0x00 -> name = "Peer is using a Public Device Address";
            case 0x01 -> name = "Peer is using a Random Device Address";
            default -> name = "Reserved for future use";
        }
        return String.format("%s (0x%02X)", name, role);
    }

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

    public String getMasterClockAccuracy() {
        String name;
        switch (masterClockAccuracy) {
            case 0x00 -> name = "500 ppm";
            case 0x01 -> name = "250 ppm";
            case 0x02 -> name = "150 ppm";
            case 0x03 -> name = "100 ppm";
            case 0x04 -> name = "75 ppm";
            case 0x05 -> name = "50 ppm";
            case 0x06 -> name = "30 ppm";
            case 0x07 -> name = "20 ppm";
            default -> name = "Reserved for future use";
        }
        return String.format("%s (0x%02X)", name, masterClockAccuracy);
    }
}
