package fr.gette.hciexplorer.hciSpecification.data.l2cap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class Frame {
    private int CID;

    public String getChannelIdentifierDescription()
    {
        return ChannelIdentifier.getDescription(this.CID);
    }
}
