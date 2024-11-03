package fr.gette.hciexplorer.hciSpecification;

import fr.gette.hciexplorer.hciSpecification.helper.BinaryMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
public class ClockOffset
{
    private BigInteger value;

    public ClockOffset(BinaryMessage data)
    {
        value = BigInteger.ZERO;
        for (int i=0; i<2; i++)
        {
            BigInteger octet = BigInteger.valueOf(data.read1octet()).shiftLeft(i*8);
            value = value.add(octet);
        }
    }

    public boolean isValid()
    {
        return value.testBit(15);
    }

    public int getCLKslaveCLKmaster()
    {
        BigInteger value14_0 = value.and(BigInteger.valueOf(0x7FFF));
        return value14_0.intValue();
    }
}
