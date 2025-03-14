package fr.gette.hciexplorer.hciSpecification.data.l2cap.attribute;

import fr.gette.hciexplorer.hciSpecification.helper.BinaryMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindByTypeValueRequest extends AttributePacket {
    AttributeOpcode opcode = AttributeOpcode.FIND_BY_TYPE_VALUE_REQUEST;
    private int startingHandle;
    private int endingHandle;
    private AttributeType attributeType;
    private AttributeValue attributeValue;

    public void setAttributeValue(BinaryMessage data)
    {
        if (attributeType.getUuid().toString().equals("00002800-0000-1000-8000-00805f9b34fb"))
        {
            attributeValue = GattService.type16bits(data.read2octets());
        }
    }
}
