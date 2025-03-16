package fr.gette.hciexplorer.hciSpecification.data.l2cap.attribute;

import fr.gette.hciexplorer.hciSpecification.helper.BinaryMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadByTypeRequest extends AttributePacket {
    AttributeOpcode opcode = AttributeOpcode.READ_BY_TYPE_REQUEST;
    private int startingHandle;
    private int endingHandle;
    private AttributeType attributeType;
}
