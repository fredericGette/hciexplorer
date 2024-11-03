package fr.gette.hciexplorer.hciSpecification.data.l2cap;

public final class ChannelIdentifier {

    private ChannelIdentifier() {
    }

    public static String getDescription(int value)
    {
        String description;
        switch(value) {
            case 0x0000 -> description = "Null identifier";
            case 0x0001 -> description = "L2CAP Signaling channel";
            case 0x0002 -> description = "Connectionless channel";
            case 0x0003 -> description = "AMP Manager Protocol";
            case 0x0004 -> description = "Attribute Protocol";
            case 0x0005 -> description = "Low Energy L2CAP Signaling channel";
            case 0x0006 -> description = "Security Manager Protocol";
            case 0x003F -> description = "AMP Test Manager";
            default -> {
                if (value >= 0x0007 && value <= 0x003E){
                    description = "Reserved";
                }
                else {
                    description = "Dynamically allocated";
                }
            }
        }
        return description;
    }
}
