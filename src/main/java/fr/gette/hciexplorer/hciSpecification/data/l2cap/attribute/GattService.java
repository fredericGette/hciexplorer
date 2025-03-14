package fr.gette.hciexplorer.hciSpecification.data.l2cap.attribute;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class GattService implements AttributeValue{
    private UUID uuid;

    public static GattService type16bits(int type)
    {
        GattService gattService = new GattService();
        gattService.uuid = UUID.fromString(String.format("0000%04X-0000-1000-8000-00805F9B34FB", type));
        return gattService;
    }

    public String getDescription()
    {
        String description;
        switch(uuid.toString())
        {
            case "00001801-0000-1000-8000-00805f9b34fb" -> description = "Generic Attribute";
            default -> description = "Unknown";
        }
        return description;
    }

    public String get16BitsUUID()
    {
        return String.format("%04X", (int)(uuid.getMostSignificantBits() >> 32) & 0xFFFF);
    }
}
