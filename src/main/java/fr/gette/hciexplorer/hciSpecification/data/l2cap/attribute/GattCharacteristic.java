package fr.gette.hciexplorer.hciSpecification.data.l2cap.attribute;

import fr.gette.hciexplorer.hciSpecification.helper.BinaryMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class GattCharacteristic implements AttributeValue{
    private short properties;
    private int valueHandle;
    private UUID uuid;

    public static GattCharacteristic type16bits(BinaryMessage data)
    {
        GattCharacteristic characteristic = new GattCharacteristic();
        characteristic.setProperties(data.read1octet());
        characteristic.setValueHandle(data.read2octets());
        characteristic.setUuid(UUID.fromString(String.format("0000%04X-0000-1000-8000-00805F9B34FB", data.read2octets())));
        return characteristic;
    }

    public String getDescription()
    {
        String description;
        switch(uuid.toString())
        {
            case "00002a00-0000-1000-8000-00805f9b34fb" -> description = "Device Name";
            case "00002a01-0000-1000-8000-00805f9b34fb" -> description = "Appearance";
            case "00002a04-0000-1000-8000-00805f9b34fb" -> description = "Peripheral Preferred Connection Parameters";
            case "00002a29-0000-1000-8000-00805f9b34fb" -> description = "Manufacturer Name String";
            case "00002a33-0000-1000-8000-00805f9b34fb" -> description = "Boot Mouse Input Report";
            case "00002a50-0000-1000-8000-00805f9b34fb" -> description = "PnP ID";
            case "00002a4a-0000-1000-8000-00805f9b34fb" -> description = "HID Information";
            case "00002a4b-0000-1000-8000-00805f9b34fb" -> description = "Report Map";
            case "00002a4c-0000-1000-8000-00805f9b34fb" -> description = "HID Control Point";
            case "00002a4d-0000-1000-8000-00805f9b34fb" -> description = "Report";
            case "00002a4e-0000-1000-8000-00805f9b34fb" -> description = "Protocol Mode";
            default -> description = "Unknown";
        }
        return description;
    }

    public String get16BitsUUID()
    {
        return String.format("%04X", (int)(uuid.getMostSignificantBits() >> 32) & 0xFFFF);
    }

    public String getProperties() {
        List<String> listProperties = new ArrayList<>();

        BigInteger binarySet = BigInteger.valueOf(properties);
        if (binarySet.testBit(0))
        {
            listProperties.add("Broadcast");
        }
        if (binarySet.testBit(1))
        {
            listProperties.add("Read");
        }
        if (binarySet.testBit(2))
        {
            listProperties.add("Write Without Response");
        }
        if (binarySet.testBit(3))
        {
            listProperties.add("Write");
        }
        if (binarySet.testBit(4))
        {
            listProperties.add("Notify");
        }
        if (binarySet.testBit(5))
        {
            listProperties.add("Indicate");
        }
        if (binarySet.testBit(6))
        {
            listProperties.add("Authenticated Signed Writes");
        }
        if (binarySet.testBit(7))
        {
            listProperties.add("Extended Properties");
        }

        return String.format("(0x%02X) %s", properties, String.join(",", listProperties));
    }
}
