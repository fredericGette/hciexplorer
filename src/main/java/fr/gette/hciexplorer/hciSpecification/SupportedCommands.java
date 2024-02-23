package fr.gette.hciexplorer.hciSpecification;

import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
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
        commandBit.put(CommandCode.PARK_STATE, 4*8+4);
        commandBit.put(CommandCode.EXIT_PARK_STATE, 4*8+5);
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
        commandBit.put(CommandCode.SET_EVENT_FILTER, 6*8+0);
        commandBit.put(CommandCode.FLUSH, 6*8+1);
        commandBit.put(CommandCode.READ_PIN_TYPE, 6*8+2);
        commandBit.put(CommandCode.WRITE_PIN_TYPE, 6*8+3);
        commandBit.put(CommandCode.CREATE_NEW_UNIT_KEY, 6*8+4);
        commandBit.put(CommandCode.READ_STORED_LINK_KEY, 6*8+5);
        commandBit.put(CommandCode.WRITE_STORED_LINK_KEY, 6*8+6);
        commandBit.put(CommandCode.DELETE_STORED_LINK_KEY, 6*8+7);
        commandBit.put(CommandCode.WRITE_LOCAL_NAME, 7*8+0);
        commandBit.put(CommandCode.READ_LOCAL_NAME, 7*8+1);
        commandBit.put(CommandCode.READ_CONNECTION_ACCEPT_TIMEOUT, 7*8+2);
        commandBit.put(CommandCode.WRITE_CONNECTION_ACCEPT_TIMEOUT, 7*8+3);
        commandBit.put(CommandCode.READ_PAGE_TIMEOUT, 7*8+4);
        commandBit.put(CommandCode.WRITE_PAGE_TIMEOUT, 7*8+5);
        commandBit.put(CommandCode.READ_SCAN_ENABLE, 7*8+6);
        commandBit.put(CommandCode.WRITE_SCAN_ENABLE, 7*8+7);
        commandBit.put(CommandCode.READ_PAGE_SCAN_ACTIVITY, 8*8+0);
        commandBit.put(CommandCode.WRITE_PAGE_SCAN_ACTIVITY, 8*8+1);
        commandBit.put(CommandCode.READ_INQUIRY_SCAN_ACTIVITY, 8*8+2);
        commandBit.put(CommandCode.WRITE_INQUIRY_SCAN_ACTIVITY, 8*8+3);
        commandBit.put(CommandCode.READ_ENCRYPTION_MODE, 8*8+6);
        commandBit.put(CommandCode.WRITE_ENCRYPTION_MODE, 8*8+7);
        commandBit.put(CommandCode.READ_CLASS_OF_DEVICE, 9*8+0);
        commandBit.put(CommandCode.WRITE_CLASS_OF_DEVICE, 9*8+1);
        commandBit.put(CommandCode.READ_VOICE_SETTING, 9*8+2);
        commandBit.put(CommandCode.WRITE_VOICE_SETTING, 9*8+3);
        commandBit.put(CommandCode.READ_AUTOMATIC_FLUSH_TIMEOUT, 9*8+4);
        commandBit.put(CommandCode.WRITE_AUTOMATIC_FLUSH_TIMEOUT, 9*8+5);
        commandBit.put(CommandCode.READ_NUM_BROADCAST_RETRANSMITS, 9*8+6);
        commandBit.put(CommandCode.WRITE_NUM_BROADCAST_RETRANSMITS, 9*8+7);
        commandBit.put(CommandCode.READ_HOLD_MODE_ACTIVITY, 10*8+0);
        commandBit.put(CommandCode.WRITE_HOLD_MODE_ACTIVITY, 10*8+1);
        commandBit.put(CommandCode.READ_TRANSMIT_POWER_LEVEL, 10*8+2);
        commandBit.put(CommandCode.READ_SYNCHRONOUS_FLOW_CONTROL_ENABLE, 10*8+3);
        commandBit.put(CommandCode.WRITE_SYNCHRONOUS_FLOW_CONTROL_ENABLE, 10*8+4);
        commandBit.put(CommandCode.SET_CONTROLLER_TO_HOST_FLOW_CONTROL, 10*8+5);
        commandBit.put(CommandCode.HOST_BUFFER_SIZE, 10*8+6);
        commandBit.put(CommandCode.HOST_NUM_COMPLETED_PACKETS, 10*8+7);
        // ...
    }

    public SupportedCommands(IoCtlMessage data)
    {
        value = BigInteger.ZERO;
        for (int i=0; i<64; i++)
        {
            BigInteger octet = BigInteger.valueOf(data.read1octet()).shiftLeft(i*8);
            value = value.add(octet);
        }
    }

    private boolean isSupported(CommandCode command)
    {
        int bit = commandBit.get(command);
        return value.testBit(bit);
    }

    public List<CommandCode> getCommands()
    {
        List<CommandCode> supportedCommands = new ArrayList<>();

        commandBit.keySet().forEach(command -> {
            if (isSupported(command))
            {
                supportedCommands.add(command);
            }
        });

        return supportedCommands;
    }

}
