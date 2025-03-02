package fr.gette.hciexplorer.hciSpecification.data.l2cap.attribute;

import fr.gette.hciexplorer.hciSpecification.data.l2cap.Frame;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class AttributePacket extends Frame {
    public abstract AttributeOpcode getOpcode();
}
