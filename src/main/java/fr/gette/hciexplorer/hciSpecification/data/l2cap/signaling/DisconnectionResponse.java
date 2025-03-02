package fr.gette.hciexplorer.hciSpecification.data.l2cap.signaling;

import fr.gette.hciexplorer.hciSpecification.data.l2cap.ChannelIdentifier;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DisconnectionResponse extends SignalingPacket {
    private SignalingCommandCode commandCode = SignalingCommandCode.DISCONNECTION_RESPONSE;
    private int destinationCID;
    private int sourceCID;

    public String getDestinationChannelIdentifierDescription()
    {
        return ChannelIdentifier.getDescription(this.destinationCID);
    }

    public String getSourceChannelIdentifierDescription()
    {
        return ChannelIdentifier.getDescription(this.sourceCID);
    }
}
