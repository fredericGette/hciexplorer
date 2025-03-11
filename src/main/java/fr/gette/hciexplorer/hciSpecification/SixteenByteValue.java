package fr.gette.hciexplorer.hciSpecification;

import fr.gette.hciexplorer.hciSpecification.helper.BinaryMessage;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class SixteenByteValue {

    BigInteger value;

    public SixteenByteValue(BinaryMessage data)
    {
        value = BigInteger.ZERO;
        for (int i=0; i<16; i++)
        {
            BigInteger octet = BigInteger.valueOf(data.read1octet()).shiftLeft(i*8);
            value = value.add(octet);
        }
    }

    public String getString() {
        return String.format("%016X", value);
    }
}
