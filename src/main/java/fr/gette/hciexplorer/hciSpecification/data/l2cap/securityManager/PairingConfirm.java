package fr.gette.hciexplorer.hciSpecification.data.l2cap.securityManager;

import fr.gette.hciexplorer.hciSpecification.ConfirmValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PairingConfirm extends SecurityManagerPacket {
    private SecurityManagerCommandCode commandCode = SecurityManagerCommandCode.PAIRING_CONFIRM;
    private ConfirmValue confirmValue;
}
