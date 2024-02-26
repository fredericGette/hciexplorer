package fr.gette.hciexplorer.hciSpecification;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public final class BluetoothCoreSpecification {

    private BluetoothCoreSpecification()
    {}
    public static String getDescription(int version)
    {
        String description;
        switch(version) {
            case 0x00 -> description = "Bluetooth® Core Specification 1.0b";
            case 0x01 -> description = "Bluetooth® Core Specification 1.1";
            case 0x02 -> description = "Bluetooth® Core Specification 1.2";
            case 0x03 -> description = "Bluetooth® Core Specification 2.0+EDR";
            case 0x04 -> description = "Bluetooth® Core Specification 2.1+EDR";
            case 0x05 -> description = "Bluetooth® Core Specification 3.0+HS";
            case 0x06 -> description = "Bluetooth® Core Specification 4.0";
            case 0x07 -> description = "Bluetooth® Core Specification 4.1 ";
            case 0x08 -> description = "Bluetooth® Core Specification 4.2 ";
            case 0x09 -> description = "Bluetooth® Core Specification 5.0 ";
            case 0x0A -> description = "Bluetooth® Core Specification 5.1 ";
            case 0x0B -> description = "Bluetooth® Core Specification 5.2";
            case 0x0C -> description = "Bluetooth® Core Specification 5.3";
            case 0x0D -> description = "Bluetooth® Core Specification 5.4";
            default -> throw new NoSuchElementException(String.format("Version 0x%02X", version));
        }
        return description;
    }

}
