package fr.gette.hciexplorer.hciSpecification.event;

import fr.gette.hciexplorer.hciSpecification.BluetoothAddress;
import fr.gette.hciexplorer.hciSpecification.SupportedLmpFeatures;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventReadRemoteSupportedFeaturesComplete extends Event {
    private EventCode code = EventCode.READ_REMOTE_SUPPORTED_FEATURES_COMPLETE;
    private ErrorCode status;
    private int connectionHandle;
    private SupportedLmpFeatures lmpFeatures;
}
