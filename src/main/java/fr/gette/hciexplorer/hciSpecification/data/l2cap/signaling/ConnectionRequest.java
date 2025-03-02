package fr.gette.hciexplorer.hciSpecification.data.l2cap.signaling;

import fr.gette.hciexplorer.hciSpecification.data.l2cap.ChannelIdentifier;
import fr.gette.hciexplorer.hciSpecification.data.l2cap.ProtocolServiceMultiplexer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConnectionRequest extends SignalingPacket {
    private SignalingCommandCode commandCode = SignalingCommandCode.CONNECTION_REQUEST;
    private int psm;
    private int sourceCID;

    public String getSourceChannelIdentifierDescription()
    {
        return ChannelIdentifier.getDescription(this.sourceCID);
    }

    public String getProtocolServiceMultiplexerDescription()
    {
        return ProtocolServiceMultiplexer.getDescription(this.psm);
    }
}
