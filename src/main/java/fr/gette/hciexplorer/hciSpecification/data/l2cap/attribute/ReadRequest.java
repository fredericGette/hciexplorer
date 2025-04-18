package fr.gette.hciexplorer.hciSpecification.data.l2cap.attribute;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadRequest extends AttributePacket {
    AttributeOpcode opcode = AttributeOpcode.READ_REQUEST;
    private int handle;
}
