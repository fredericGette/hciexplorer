package fr.gette.hciexplorer.hciSpecification.data.l2cap.securityManager;

import fr.gette.hciexplorer.hciSpecification.EightByteValue;
import fr.gette.hciexplorer.hciSpecification.SixteenByteValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SigningInformation extends SecurityManagerPacket {
    private SecurityManagerCommandCode commandCode = SecurityManagerCommandCode.SIGNING_INFORMATION;
    private SixteenByteValue signatureKey;
}
