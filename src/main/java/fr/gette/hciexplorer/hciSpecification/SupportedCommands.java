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
        commandBit.put(CommandCode.ACCEPT_CONNECTION_REQUEST, 1*8+0);
        commandBit.put(CommandCode.REJECT_CONNECTION_REQUEST, 1*8+1);
        commandBit.put(CommandCode.LINK_KEY_REQUEST_REPLY, 1*8+2);
        commandBit.put(CommandCode.LINK_KEY_REQUEST_NEGATIVE_REPLY, 1*8+3);
        commandBit.put(CommandCode.PIN_CODE_REQUEST_REPLY, 1*8+4);
        commandBit.put(CommandCode.PIN_CODE_REQUEST_NEGATIVE_REPLY, 1*8+5);
        commandBit.put(CommandCode.CHANGE_CONNECTION_PACKET_TYPE, 1*8+6);
        commandBit.put(CommandCode.AUTHENTICATION_REQUESTED, 1*8+7);
        commandBit.put(CommandCode.SET_CONNECTION_ENCRYPTION, 2*8+0);
        commandBit.put(CommandCode.CHANGE_CONNECTION_LINK_KEY, 2*8+1);
        commandBit.put(CommandCode.CENTRAL_LINK_KEY, 2*8+2);
        commandBit.put(CommandCode.REMOTE_NAME_REQUEST, 2*8+3);
        commandBit.put(CommandCode.REMOTE_NAME_REQUEST_CANCEL, 2*8+4);
        commandBit.put(CommandCode.READ_REMOTE_SUPPORTED_FEATURES, 2*8+5);
        commandBit.put(CommandCode.READ_REMOTE_EXTENDED_FEATURES, 2*8+6);
        commandBit.put(CommandCode.READ_REMOTE_VERSION_INFORMATION, 2*8+7);
        commandBit.put(CommandCode.READ_CLOCK_OFFSET, 3*8+0);
        commandBit.put(CommandCode.READ_LMP_HANDLE, 3*8+1);
        commandBit.put(CommandCode.HOLD_MODE, 4*8+1);
        commandBit.put(CommandCode.SNIFF_MODE, 4*8+2);
        commandBit.put(CommandCode.EXIT_SNIFF_MODE, 4*8+3);
        commandBit.put(CommandCode.PARK_MODE, 4*8+4);
        commandBit.put(CommandCode.EXIT_PARK_MODE, 4*8+5);
        commandBit.put(CommandCode.QOS_SETUP, 4*8+6);
        commandBit.put(CommandCode.ROLE_DISCOVERY, 4*8+7);
        commandBit.put(CommandCode.SWITCH_ROLE, 5*8+0);
        commandBit.put(CommandCode.READ_LINK_POLICY_SETTINGS, 5*8+1);
        commandBit.put(CommandCode.WRITE_LINK_POLICY_SETTINGS, 5*8+2);
        commandBit.put(CommandCode.READ_DEFAULT_LINK_POLICY_SETTINGS, 5*8+3);
        commandBit.put(CommandCode.WRITE_DEFAULT_LINK_POLICY_SETTINGS, 5*8+4);
        commandBit.put(CommandCode.FLOW_SPECIFICATION, 5*8+5);
        commandBit.put(CommandCode.SET_EVENT_MASK, 5*8+6);
        commandBit.put(CommandCode.RESET, 5*8+7);
        // ...
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
