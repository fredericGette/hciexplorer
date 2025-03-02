package fr.gette.hciexplorer.hciSpecification.data.l2cap.attribute;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeMTURequest extends AttributePacket {
    AttributeOpcode opcode = AttributeOpcode.EXCHANGE_MTU_REQUEST;
    private int clientRxMTU;
}
