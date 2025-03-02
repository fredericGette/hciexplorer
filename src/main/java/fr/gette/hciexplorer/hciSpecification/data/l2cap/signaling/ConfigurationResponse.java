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
public class ConfigurationResponse extends SignalingPacket {
    private SignalingCommandCode commandCode = SignalingCommandCode.CONFIGURATION_RESPONSE;
    private int sourceCID;
    private ConfigurationFlags flags;
    private int result;
    private List<ConfigurationParameter> options;

    public ConfigurationResponse(BinaryMessage data, int length) {
        setSourceCID(data.read2octets());
        setFlags(ConfigurationFlags.get(data.read2octets()));
        setResult(data.read2octets());
        options = new ArrayList<>();
        if (length > 0x0006) {
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

    public String getSourceChannelIdentifierDescription()
    {
        return ChannelIdentifier.getDescription(this.sourceCID);
    }

    public String getResultDescription()
    {
        String description;
        switch (this.result) {
            case 0x0000 -> description = "Success";
            case 0x0001 -> description = "Failure – unacceptable parameters";
            case 0x0002 -> description = "Failure – rejected (no reason provided)";
            case 0x0003 -> description = "Failure – unknown options";
            case 0x0004 -> description = "Pending";
            case 0x0005 -> description = "Failure - flow spec rejected";
            default -> description = "Reserved";
        }

        return String.format("0x%04X - %s", this.result, description);
    }
}
