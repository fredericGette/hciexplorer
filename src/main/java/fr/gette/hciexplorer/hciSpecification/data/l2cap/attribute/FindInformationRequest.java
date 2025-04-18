package fr.gette.hciexplorer.hciSpecification.data.l2cap.attribute;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindInformationRequest extends AttributePacket {
    AttributeOpcode opcode = AttributeOpcode.FIND_INFORMATION_REQUEST;
    private int startingHandle;
    private int endingHandle;
}
