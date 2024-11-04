package fr.gette.hciexplorer.hciSpecification.data.l2cap.signaling;

import fr.gette.hciexplorer.hciSpecification.data.l2cap.ChannelIdentifier;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InvalidCIDInRequest extends CommandReject {
    private String reasonDescription = "Invalid CID in request";
    private int localCID;
    private int remoteCID;

    public String getLocalChannelIdentifierDescription()
    {
        return ChannelIdentifier.getDescription(this.localCID);
    }

    public String geRemoteChannelIdentifierDescription()
    {
        return ChannelIdentifier.getDescription(this.remoteCID);
    }
}
