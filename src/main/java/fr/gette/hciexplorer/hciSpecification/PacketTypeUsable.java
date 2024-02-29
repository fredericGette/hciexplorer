package fr.gette.hciexplorer.hciSpecification;

import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlMessage;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class PacketTypeUsable {

    BigInteger value;

    private static final Map<PacketType, Integer> packetTypeBit = new HashMap<>();
    static {
        packetTypeBit.put(PacketType.NOT_2_DH1, 0*8+1);
        packetTypeBit.put(PacketType.NOT_3_DH1, 0*8+2);
        packetTypeBit.put(PacketType.DM1, 0*8+3);
        packetTypeBit.put(PacketType.DH1, 0*8+4);
        packetTypeBit.put(PacketType.NOT_2_DH3, 1*8+0);
        packetTypeBit.put(PacketType.NOT_3_DH3, 1*8+1);
        packetTypeBit.put(PacketType.DM3, 1*8+2);
        packetTypeBit.put(PacketType.DH3, 1*8+3);
        packetTypeBit.put(PacketType.NOT_2_DH5, 1*8+4);
        packetTypeBit.put(PacketType.NOT_3_DH5, 1*8+5);
        packetTypeBit.put(PacketType.DM5, 1*8+6);
        packetTypeBit.put(PacketType.DH5, 1*8+7);
    }

    public PacketTypeUsable(IoCtlMessage data)
    {
        value = BigInteger.ZERO;
        for (int i=0; i<2; i++)
        {
            BigInteger octet = BigInteger.valueOf(data.read1octet()).shiftLeft(i*8);
            value = value.add(octet);
        }
    }

    private boolean isUsable(PacketType packetType)
    {
        int bit = packetTypeBit.get(packetType);
        return value.testBit(bit);
    }

    public List<PacketType> getUsablePacketTypes()
    {
        List<PacketType> usablePacketTypes = new ArrayList<>();

        packetTypeBit.keySet().forEach(packetType -> {
            if (isUsable(packetType))
            {
                usablePacketTypes.add(packetType);
            }
        });

        return usablePacketTypes;
    }

}
