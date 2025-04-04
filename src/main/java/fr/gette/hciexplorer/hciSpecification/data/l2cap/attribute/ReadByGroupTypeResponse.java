package fr.gette.hciexplorer.hciSpecification.data.l2cap.attribute;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadByGroupTypeResponse extends AttributePacket {
    AttributeOpcode opcode = AttributeOpcode.READ_BY_GROUP_TYPE_RESPONSE;
    private AttributeDataList attributeDataList;
}
