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
public class SupportedLeFeatures
{

    BigInteger value;

    private static final Map<LeFeature, Integer> featureBit = new HashMap<>();
    static {
        featureBit.put(LeFeature.LE_ENCRYPTION, 0*8+0);
    }

    public SupportedLeFeatures(BinaryMessage data)
    {
        value = BigInteger.ZERO;
        for (int i=0; i<8; i++)
        {
            BigInteger octet = BigInteger.valueOf(data.read1octet()).shiftLeft(i*8);
            value = value.add(octet);
        }
    }

    private boolean isSupported(LeFeature feature)
    {
        int bit = featureBit.get(feature);
        return value.testBit(bit);
    }

    public List<LeFeature> getFeatures()
    {
        List<LeFeature> supportedLeFeatures = new ArrayList<>();

        featureBit.keySet().forEach(feature -> {
            if (isSupported(feature))
            {
                supportedLeFeatures.add(feature);
            }
        });

        return supportedLeFeatures;
    }

}
