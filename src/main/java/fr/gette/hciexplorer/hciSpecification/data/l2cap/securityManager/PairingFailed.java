package fr.gette.hciexplorer.hciSpecification.data.l2cap.securityManager;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PairingFailed extends SecurityManagerPacket {
    private SecurityManagerCommandCode commandCode = SecurityManagerCommandCode.PAIRING_FAILED;
    private short reason;

    public String getReason(){
        String name;
        switch (reason) {
            case 0x00 -> name = "Reserved";
            case 0x01 -> name = "Passkey Entry Failed";
            case 0x02 -> name = "OOB Not Available";
            case 0x03 -> name = "Authentication Requirements";
            case 0x04 -> name = "Confirm Value Failed";
            case 0x05 -> name = "Pairing Not Supported";
            case 0x06 -> name = "Encryption Key Size";
            case 0x07 -> name = "Command Not Supported";
            case 0x08 -> name = "Unspecified Reason";
            case 0x09 -> name = "Repeated Attempts";
            default -> name = "Reserved";
        }
        return String.format("%s (0x%02X)", name, reason);
    }
}
