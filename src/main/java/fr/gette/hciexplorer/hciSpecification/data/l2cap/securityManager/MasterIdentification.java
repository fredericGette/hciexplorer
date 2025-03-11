package fr.gette.hciexplorer.hciSpecification.data.l2cap.securityManager;

import fr.gette.hciexplorer.hciSpecification.EightByteValue;
import fr.gette.hciexplorer.hciSpecification.SixteenByteValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterIdentification extends SecurityManagerPacket {
    private SecurityManagerCommandCode commandCode = SecurityManagerCommandCode.MASTER_IDENTIFICATION;
    private int ediv;
    private EightByteValue rand;

    public String getEdiv() {
        return String.format("%02X", ediv);
    }
}
