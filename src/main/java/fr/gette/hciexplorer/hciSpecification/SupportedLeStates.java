package fr.gette.hciexplorer.hciSpecification;

import fr.gette.hciexplorer.hciSpecification.helper.BinaryMessage;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SupportedLeStates
{

    BigInteger value;

    private static final Map<LeState, Integer> stateBit = new HashMap<>();
    static {
        stateBit.put(LeState.NON_CONNECTABLE_ADVERTISING, 0*8+0);
        stateBit.put(LeState.SCANNABLE_ADVERTISING, 0*8+1);
        stateBit.put(LeState.CONNECTABLE_ADVERTISING, 0*8+2);
        stateBit.put(LeState.DIRECTED_ADVERTISING, 0*8+3);
        stateBit.put(LeState.PASSIVE_SCANNING, 0*8+4);
        stateBit.put(LeState.ACTIVE_SCANNING, 0*8+5);
        stateBit.put(LeState.INITIATING_AND_CONNECTION_IN_MASTER_ROLE, 0*8+6);
        stateBit.put(LeState.CONNECTION_IN_SLAVE_ROLE, 0*8+7);
        stateBit.put(LeState.NON_CONNECTABLE_ADVERTISING_AND_PASSIVE_SCANNING_COMBINATION, 1*8+0);
        stateBit.put(LeState.SCANNABLE_ADVERTISING_AND_PASSIVE_SCANNING_COMBINATION, 1*8+1);
        stateBit.put(LeState.CONNECTABLE_ADVERTISING_AND_PASSIVE_SCANNING_COMBINATION, 1*8+2);
        stateBit.put(LeState.DIRECTED_ADVERTISING_AND_PASSIVE_SCANNING_COMBINATION, 1*8+3);
        stateBit.put(LeState.NON_CONNECTABLE_ADVERTISING_AND_ACTIVE_SCANNING_COMBINATION, 1*8+4);
        stateBit.put(LeState.SCANNABLE_ADVERTISING_AND_ACTIVE_SCANNING_COMBINATION, 1*8+5);
        stateBit.put(LeState.CONNECTABLE_ADVERTISING_AND_ACTIVE_SCANNING_COMBINATION, 1*8+6);
        stateBit.put(LeState.DIRECTED_ADVERTISING_AND_ACTIVE_SCANNING_COMBINATION, 1*8+7);
        stateBit.put(LeState.NON_CONNECTABLE_ADVERTISING_AND_INITIATING_COMBINATION, 2*8+0);
        stateBit.put(LeState.SCANNABLE_ADVERTISING_AND_INITIATING_COMBINATION, 2*8+1);
        stateBit.put(LeState.NON_CONNECTABLE_ADVERTISING_AND_MASTER_ROLE_COMBINATION, 2*8+2);
        stateBit.put(LeState.SCANNABLE_ADVERTISING_AND_MASTER_ROLE_COMBINATION, 2*8+3);
        stateBit.put(LeState.NON_CONNECTABLE_ADVERTISING_AND_SLAVE_ROLE_COMBINATION, 2*8+4);
        stateBit.put(LeState.SCANNABLE_ADVERTISING_AND_SLAVE_ROLE_COMBINATION, 2*8+5);
        stateBit.put(LeState.PASSIVE_SCANNING_AND_INITIATING_COMBINATION, 2*8+6);
        stateBit.put(LeState.ACTIVE_SCANNING_AND_INITIATING_COMBINATION, 2*8+7);
        stateBit.put(LeState.PASSIVE_SCANNING_AND_MASTER_ROLE_COMBINATION, 3*8+0);
        stateBit.put(LeState.ACTIVE_SCANNING_AND_MASTER_ROLE_COMBINATION, 3*8+1);
        stateBit.put(LeState.PASSIVE_SCANNING_AND_SLAVE_ROLE_COMBINATION, 3*8+2);
        stateBit.put(LeState.ACTIVE_SCANNING_AND_SLAVE_ROLE_COMBINATION, 3*8+3);
        stateBit.put(LeState.INITIATING_AND_MASTER_ROLE_COMBINATION, 3*8+4);
    }

    public SupportedLeStates(BinaryMessage data)
    {
        value = BigInteger.ZERO;
        for (int i=0; i<8; i++)
        {
            BigInteger octet = BigInteger.valueOf(data.read1octet()).shiftLeft(i*8);
            value = value.add(octet);
        }
    }

    private boolean isSupported(LeState state)
    {
        int bit = stateBit.get(state);
        return value.testBit(bit);
    }

    public List<LeState> getStates()
    {
        List<LeState> supportedLeStates = new ArrayList<>();

        stateBit.keySet().forEach(state -> {
            if (isSupported(state))
            {
                supportedLeStates.add(state);
            }
        });

        return supportedLeStates;
    }

}
