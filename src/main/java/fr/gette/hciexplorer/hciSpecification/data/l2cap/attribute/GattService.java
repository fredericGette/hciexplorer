package fr.gette.hciexplorer.hciSpecification.data.l2cap.attribute;

import fr.gette.hciexplorer.hciSpecification.helper.BinaryMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class GattService implements AttributeValue{
    private UUID uuid;

    public static GattService type16bits(BinaryMessage data)
    {
        GattService service = new GattService();
        service.setUuid(UUID.fromString(String.format("0000%04X-0000-1000-8000-00805F9B34FB", data.read2octets())));
        return service;
    }

    public String getDescription()
    {
        String description;
        switch(uuid.toString())
        {
            case "00001800-0000-1000-8000-00805f9b34fb" -> description = "Generic Access";
            case "00001801-0000-1000-8000-00805f9b34fb" -> description = "Generic Attribute";
            case "0000180a-0000-1000-8000-00805f9b34fb" -> description = "Device Information";
            case "00001812-0000-1000-8000-00805f9b34fb" -> description = "Human Interface Device";
            default -> description = "Unknown";
        }
        return description;
    }

    public String get16BitsUUID()
    {
        return String.format("%04X", (int)(uuid.getMostSignificantBits() >> 32) & 0xFFFF);
    }
}
