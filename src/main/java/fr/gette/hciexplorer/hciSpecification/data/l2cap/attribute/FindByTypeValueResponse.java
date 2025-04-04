package fr.gette.hciexplorer.hciSpecification.data.l2cap.attribute;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindByTypeValueResponse extends AttributePacket {
    AttributeOpcode opcode = AttributeOpcode.FIND_BY_TYPE_VALUE_RESPONSE;
    private int foundAttributeHandle;
    private int groupEndHandle;
}
