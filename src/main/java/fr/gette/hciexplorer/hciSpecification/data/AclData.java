package fr.gette.hciexplorer.hciSpecification.data;

import fr.gette.hciexplorer.hciSpecification.AclDirection;
import fr.gette.hciexplorer.hciSpecification.HciMessage;
import fr.gette.hciexplorer.hciSpecification.HciPacketType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.NoSuchElementException;

@Getter
@Setter
@NoArgsConstructor
public class AclData extends HciMessage {
    private HciPacketType type = HciPacketType.AclData;
    private AclDirection direction;
    private int handle;
    private short packetBoundaryFlag;
    private short broadcastFlag;
    private int dataTotalLength;
    private short[] data;

    public String getPacketBoundaryFlagDescription()
    {
        String description;
        switch(packetBoundaryFlag)
        {
            case 0 -> description = "First non-automatically-flushable packet of Higher Layer Message.";
            case 1 -> description = "Continuing fragment of Higher Layer Message.";
            case 2 -> description = "First automatically flushable packet of Higher Layer Message.";
            case 3 -> description = "A complete L2CAP PDU. Automatically flushable.";
            default -> throw new NoSuchElementException(String.format("value %d", packetBoundaryFlag));
        }
        return description;
    }

    public String getBroadcastFlagDescription()
    {
        String description;
        switch (direction){
            case HOST_TO_CONTROLLER ->  {
                switch(broadcastFlag)
                {
                    case 0 -> description = "No broadcast.";
                    case 1 -> description = "Active Slave Broadcast.";
                    case 2 -> description = "Parked Slave Broadcast.";
                    default -> throw new NoSuchElementException(String.format("value %d", packetBoundaryFlag));
                }
            }
            case CONTROLLER_TO_HOST ->
            {
                switch(broadcastFlag)
                {
                    case 0 -> description = "Point-to-point.";
                    case 1 -> description = "BR/EDR Packet received as a slave not in park state.";
                    case 2 -> description = "BR/EDR Packet received as a slave in park state.";
                    default -> throw new NoSuchElementException(String.format("value %d", packetBoundaryFlag));
                }
            }
            default -> throw new NoSuchElementException("No direction");
        }
        return description;
    }
}
