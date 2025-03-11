package fr.gette.hciexplorer.hciSpecification.data.l2cap.securityManager;

import fr.gette.hciexplorer.hciSpecification.SixteenByteValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PairingRandom extends SecurityManagerPacket {
    private SecurityManagerCommandCode commandCode = SecurityManagerCommandCode.PAIRING_RANDOM;
    private SixteenByteValue randomValue;
}
