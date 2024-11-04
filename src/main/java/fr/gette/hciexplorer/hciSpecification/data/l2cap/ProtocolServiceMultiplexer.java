package fr.gette.hciexplorer.hciSpecification.data.l2cap;

import java.util.NoSuchElementException;

public final class ProtocolServiceMultiplexer {

    private ProtocolServiceMultiplexer() {
    }

    public static String getDescription(int value)
    {
        String description;
        switch(value) {
            case 0x0001 -> description = "SDP";
            case 0x0003 -> description = "RFCOMM";
            case 0x0005 -> description = "TCS-BIN";
            case 0x0007 -> description = "TCS-BIN-CORDLESS";
            case 0x000F -> description = "BNEP";
            case 0x0011 -> description = "HID_Control";
            case 0x0013 -> description = "HID_Interrupt";
            case 0x0015 -> description = "UPnP";
            case 0x0017 -> description = "AVCTP";
            case 0x0019 -> description = "AVDTP";
            case 0x001B -> description = "AVCTP_Browsing";
            case 0x001D -> description = "UDI_C-Plane";
            case 0x001F -> description = "ATT";
            case 0x0021 -> description = "3DSP";
            case 0x0023 -> description = "LE_PSM_IPSP";
            case 0x0025 -> description = "OTS";
            case 0x0027 -> description = "EATT";
            default -> {
                if (value <= 0x0EFF){
                    description = "Fixed";
                }
                else if (value >= 0x1000){
                    description = "Dynamic";
                }
                else throw new NoSuchElementException(String.format("PSM 0x%04X", value));
            }
        }
        return String.format("0x%04X - %s", value, description);
    }
}
