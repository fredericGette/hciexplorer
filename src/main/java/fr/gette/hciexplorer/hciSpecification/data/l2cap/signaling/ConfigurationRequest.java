package fr.gette.hciexplorer.hciSpecification.data.l2cap.signaling;

import fr.gette.hciexplorer.hciSpecification.data.l2cap.ChannelIdentifier;
import fr.gette.hciexplorer.hciSpecification.data.l2cap.signaling.configurationParameter.ConfigurationParameter;
import fr.gette.hciexplorer.hciSpecification.data.l2cap.signaling.configurationParameter.ConfigurationParameterType;
import fr.gette.hciexplorer.hciSpecification.data.l2cap.signaling.configurationParameter.MaximumTransmissionUnit;
import fr.gette.hciexplorer.hciSpecification.helper.BinaryMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Getter
@Setter
public class ConfigurationRequest extends SignalingPacket {
    private SignalingCommandCode commandCode = SignalingCommandCode.CONFIGURATION_REQUEST;
    private int destinationCID;
    private ConfigurationFlags flags;
    private List<ConfigurationParameter> options;

    public ConfigurationRequest(BinaryMessage data, int length) {
        setDestinationCID(data.read2octets());
        setFlags(ConfigurationFlags.get(data.read2octets()));

        options = new ArrayList<>();
        if (length > 0x0004) {
            do {
                ConfigurationParameterType type = ConfigurationParameterType.get(data.read1octet());
                short parameterLength = data.read1octet();
                switch(type){
                    case MAXIMUM_TRANSMISSION_UNIT -> {
                        MaximumTransmissionUnit option = new MaximumTransmissionUnit();
                        option.setMtu(data.read2octets());
                        options.add(option);
                    }
                    default ->  throw new NoSuchElementException(String.format("%s",type));
                }
            } while (data.hasRemaining());
        }
    }

    public String getDestinationChannelIdentifierDescription()
    {
        return ChannelIdentifier.getDescription(this.destinationCID);
    }
}
