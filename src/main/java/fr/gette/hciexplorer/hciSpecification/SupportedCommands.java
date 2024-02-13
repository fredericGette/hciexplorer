package fr.gette.hciexplorer.hciSpecification;

import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.event.EventCode;
import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlMessage;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class SupportedCommands {

    BigInteger value;

    private static final Map<CommandCode, Integer> commandBit = new HashMap<>();
    static {
        commandBit.put(CommandCode.INQUIRY, 0*8+0);
        commandBit.put(CommandCode.INQUIRY_CANCEL, 0*8+1);
        commandBit.put(CommandCode.PERIODIC_INQUIRY_MODE, 0*8+2);
        commandBit.put(CommandCode.EXIT_PERIODIC_INQUIRY_MODE, 0*8+3);
        commandBit.put(CommandCode.CREATE_CONNECTION, 0*8+4);
        commandBit.put(CommandCode.DISCONNECT, 0*8+5);
        commandBit.put(CommandCode.ADD_SCO_CONNECTION, 0*8+6);
        commandBit.put(CommandCode.CREATE_CONNECTION_CANCEL, 0*8+7);
    }

    public SupportedCommands(IoCtlMessage data)
    {
        value = BigInteger.ZERO;
        for (int i=0; i<64; i++)
        {
            BigInteger octet = BigInteger.valueOf(data.readUChar()).shiftLeft(i*8);
            value = value.add(octet);
        }
    }

    public boolean isSupported(CommandCode command)
    {
        int bit = commandBit.get(command);
        return value.testBit(bit);
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        commandBit.keySet().forEach(command -> buffer.append(String.format("%s:%s,",command,isSupported(command))));
        return buffer.toString();
    }
}
