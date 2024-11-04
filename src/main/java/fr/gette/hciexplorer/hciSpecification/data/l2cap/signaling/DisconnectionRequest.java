package fr.gette.hciexplorer.hciSpecification.data.l2cap.signaling;

import fr.gette.hciexplorer.hciSpecification.data.l2cap.ChannelIdentifier;
import fr.gette.hciexplorer.hciSpecification.data.l2cap.ProtocolServiceMultiplexer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DisconnectionRequest extends SignalingPacket {
    private CommandCode commandCode = CommandCode.DISCONNECTION_REQUEST;
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
