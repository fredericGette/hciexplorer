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
public class LAP
{

    BigInteger value;

    private static final Map<Integer, LowerAddressPart> valueLowerAddressPart = new HashMap<>();
    static {
        valueLowerAddressPart.put(0x9E8B33, LowerAddressPart.GIAC);
        valueLowerAddressPart.put(0x9E8B00, LowerAddressPart.LIAC);
    }

    public LAP(IoCtlMessage data)
    {
        value = BigInteger.ZERO;
        for (int i=0; i<3; i++)
        {
            BigInteger octet = BigInteger.valueOf(data.read1octet()).shiftLeft(i*8);
            value = value.add(octet);
        }
    }

    public int getValue()
    {
        return value.intValue();
    }

    public String getName()
    {
        return valueLowerAddressPart.get(value.intValue()).name();
    }

}
