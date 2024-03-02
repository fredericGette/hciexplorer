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
public class LinkKey {

    BigInteger value;

    public LinkKey(IoCtlMessage data)
    {
        value = BigInteger.ZERO;
        for (int i=0; i<16; i++)
        {
            BigInteger octet = BigInteger.valueOf(data.read1octet()).shiftLeft(i*8);
            value = value.add(octet);
        }
    }

}
