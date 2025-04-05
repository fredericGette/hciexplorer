package fr.gette.hciexplorer.hciSpecification.data.l2cap.attribute;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadByTypeResponse extends AttributePacket {
    AttributeOpcode opcode = AttributeOpcode.READ_BY_TYPE_RESPONSE;
    private AttributeDataList attributeDataList;
}
