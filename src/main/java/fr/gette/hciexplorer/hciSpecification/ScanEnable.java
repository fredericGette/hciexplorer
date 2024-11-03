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
public class ScanEnable {

    BigInteger value;

    private static final Map<ScanType, Integer> scanTypeBit = new HashMap<>();
    static {
        scanTypeBit.put(ScanType.INQUIRY, 0*8+1);
        scanTypeBit.put(ScanType.PAGE, 0*8+2);
    }

    public ScanEnable(BinaryMessage data)
    {
        value = BigInteger.ZERO;
        for (int i=0; i<1; i++)
        {
            BigInteger octet = BigInteger.valueOf(data.read1octet()).shiftLeft(i*8);
            value = value.add(octet);
        }
    }

    private boolean isEnabled(ScanType scanType)
    {
        int bit = scanTypeBit.get(scanType);
        return value.testBit(bit);
    }

    public List<ScanType> getEnabledScanTypes()
    {
        List<ScanType> enabledScanTypes = new ArrayList<>();

        scanTypeBit.keySet().forEach(scanType -> {
            if (isEnabled(scanType))
            {
                enabledScanTypes.add(scanType);
            }
        });

        return enabledScanTypes;
    }

}
