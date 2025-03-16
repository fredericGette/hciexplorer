package fr.gette.hciexplorer.hciSpecification.data.l2cap.attribute;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeMTUResponse extends AttributePacket {
    AttributeOpcode opcode = AttributeOpcode.EXCHANGE_MTU_RESPONSE;
    private int serverRxMTU;
}
