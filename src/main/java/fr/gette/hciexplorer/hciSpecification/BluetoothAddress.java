package fr.gette.hciexplorer.hciSpecification;

import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlMessage;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class BluetoothAddress {

    BigInteger value;

    public BluetoothAddress(IoCtlMessage data)
    {
        value = BigInteger.ZERO;
        for (int i=0; i<6; i++)
        {
            BigInteger octet = BigInteger.valueOf(data.read1octet()).shiftLeft(i*8);
            value = value.add(octet);
        }
    }

    public String getString() {
        String string = value.toString(16);
        return string.replaceAll("(.{2})(?!$)", "$1:").toUpperCase();
    }
}
