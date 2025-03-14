package fr.gette.hciexplorer.hciSpecification.data.l2cap.attribute;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AttributeType {
    private UUID uuid;

    public static AttributeType type16bits(int type)
    {
        AttributeType attributeType = new AttributeType();
        attributeType.uuid = UUID.fromString(String.format("0000%04X-0000-1000-8000-00805F9B34FB", type));
        return attributeType;
    }

    public String getDescription()
    {
        String description;
        switch(uuid.toString())
        {
            case "00002800-0000-1000-8000-00805f9b34fb" -> description = "GATT Primary Service Declaration";
            default -> description = "Unknown";
        }
        return description;
    }

    public String get16BitsUUID()
    {
        return String.format("%04X", (int)(uuid.getMostSignificantBits() >> 32) & 0xFFFF);
    }
}
