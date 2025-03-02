package fr.gette.hciexplorer.hciSpecification.data.l2cap.securityManager;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PairingResponse extends SecurityManagerPacket {
    private SecurityManagerCommandCode commandCode = SecurityManagerCommandCode.PAIRING_RESPONSE;
    private short iOCapability;
    private short oOBDataFlag;
    private short authReq;
    private short maximumEncryptionKeySize;
    private short initiatorKeyDistribution;
    private short responderKeyDistribution;

    public String getIOCapability(){
        String name;
        switch (iOCapability) {
            case 0x00 -> name = "DisplayOnly";
            case 0x01 -> name = "DisplayYesNo";
            case 0x02 -> name = "KeyboardOnly";
            case 0x03 -> name = "NoInputNoOutput";
            case 0x04 -> name = "KeyboardDisplay";
            default -> name = "Reserved";
        }
        return String.format("%s (0x%02X)", name, iOCapability);
    }

    public String getOOBDataFlag(){
        String name;
        switch (oOBDataFlag) {
            case 0x00 -> name = "OOB Authentication data not present";
            case 0x01 -> name = "OOB Authentication data from remote device present";
            default -> name = "Reserved";
        }
        return String.format("%s (0x%02X)", name, iOCapability);
    }
}
