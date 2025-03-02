package fr.gette.hciexplorer.hciSpecification.data.l2cap.signaling;

import fr.gette.hciexplorer.hciSpecification.data.l2cap.ChannelIdentifier;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConnectionResponse extends SignalingPacket {
    private SignalingCommandCode commandCode = SignalingCommandCode.CONNECTION_RESPONSE;
    private int destinationCID;
    private int sourceCID;
    private int result;
    private int status;

    public String getDestinationChannelIdentifierDescription()
    {
        return ChannelIdentifier.getDescription(this.destinationCID);
    }

    public String getSourceChannelIdentifierDescription()
    {
        return ChannelIdentifier.getDescription(this.sourceCID);
    }

    public String getResultDescription()
    {
        String description;
        switch (this.result) {
            case 0x0000 -> description = "Connection successful";
            case 0x0001 -> description = "Connection pending";
            case 0x0002 -> description = "Connection refused – PSM not supported";
            case 0x0003 -> description = "Connection refused – security block";
            case 0x0004 -> description = "Connection refused – no resources available";
            default -> description = "Reserved";
        }

        return String.format("0x%04X - %s", this.result, description);
    }

    public String getStatusDescription()
    {
        String description;
        switch (this.status) {
            case 0x0000 -> description = "No further information available";
            case 0x0001 -> description = "Authentication pending";
            case 0x0002 -> description = "Authorization pending";
            default -> description = "Reserved";
        }

        return String.format("0x%04X - %s", this.status, description);
    }
}
