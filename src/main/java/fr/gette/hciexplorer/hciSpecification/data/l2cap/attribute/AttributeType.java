package fr.gette.hciexplorer.hciSpecification.data.l2cap.attribute;

import fr.gette.hciexplorer.hciSpecification.helper.BinaryMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AttributeType {
    private UUID uuid;

    public AttributeType(int twoBytes)
    {
        uuid = UUID.fromString(String.format("0000%04X-0000-1000-8000-00805F9B34FB", twoBytes));
    }

    public AttributeType(BinaryMessage data)
    {
        if (data.getDataLengthRemaining() == 2)
        {
            uuid = UUID.fromString(String.format("0000%04X-0000-1000-8000-00805F9B34FB", data.read2octets()));
        }
        else
        {
            throw new UnsupportedOperationException("Cannot create 16 bytes UUID");
        }
    }

    public String getDescription()
    {
        String description;
        switch(uuid.toString())
        {
            case "00002800-0000-1000-8000-00805f9b34fb" -> description = "GATT Primary Service Declaration";
            case "00002b3a-0000-1000-8000-00805f9b34fb" -> description = "Server Supported Features";
            default -> description = "Unknown";
        }
        return description;
    }

    public String get16BitsUUID()
    {
        return String.format("%04X", (int)(uuid.getMostSignificantBits() >> 32) & 0xFFFF);
    }
}
