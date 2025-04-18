package fr.gette.hciexplorer.hciSpecification;

import fr.gette.hciexplorer.hciSpecification.helper.BinaryMessage;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class LinkKey {

    BigInteger value;

    public LinkKey(BinaryMessage data)
    {
        value = BigInteger.ZERO;
        for (int i=0; i<16; i++)
        {
            BigInteger octet = BigInteger.valueOf(data.read1octet()).shiftLeft(i*8);
            value = value.add(octet);
        }
    }

}
