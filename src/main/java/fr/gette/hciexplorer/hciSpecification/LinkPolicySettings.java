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
public class LinkPolicySettings {

    BigInteger value;

    private static final Map<LinkPolicy, Integer> linkPolicyBit = new HashMap<>();
    static {
        linkPolicyBit.put(LinkPolicy.ROLE_SWITCH, 0*8+0);
        linkPolicyBit.put(LinkPolicy.HOLD_MODE, 0*8+1);
        linkPolicyBit.put(LinkPolicy.SNIFF_MODE, 0*8+2);
        linkPolicyBit.put(LinkPolicy.PARK_STATE, 0*8+3);
    }

    public LinkPolicySettings(IoCtlMessage data)
    {
        value = BigInteger.ZERO;
        for (int i=0; i<2; i++)
        {
            BigInteger octet = BigInteger.valueOf(data.read1octet()).shiftLeft(i*8);
            value = value.add(octet);
        }
    }

    private boolean isEnabled(LinkPolicy linkPolicy)
    {
        int bit = linkPolicyBit.get(linkPolicy);
        return value.testBit(bit);
    }

    public List<LinkPolicy> getEnabledLinkPolicies()
    {
        List<LinkPolicy> enabledLinkPolicies = new ArrayList<>();

        linkPolicyBit.keySet().forEach(linkPolicy -> {
            if (isEnabled(linkPolicy))
            {
                enabledLinkPolicies.add(linkPolicy);
            }
        });

        return enabledLinkPolicies;
    }

}
