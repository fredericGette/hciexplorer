package fr.gette.hciexplorer.hciSpecification.data.l2cap.signaling.configurationParameter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MaximumTransmissionUnit implements ConfigurationParameter {
private ConfigurationParameterType type = ConfigurationParameterType.MAXIMUM_TRANSMISSION_UNIT;
    private int mtu;
}
