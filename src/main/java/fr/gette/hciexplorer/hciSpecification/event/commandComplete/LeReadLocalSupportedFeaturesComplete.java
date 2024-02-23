package fr.gette.hciexplorer.hciSpecification.event.commandComplete;

import fr.gette.hciexplorer.hciSpecification.SupportedLeFeatures;
import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LeReadLocalSupportedFeaturesComplete extends EventCommandComplete {
    private CommandCode commandOpCode = CommandCode.LE_READ_LOCAL_SUPPORTED_FEATURES;
    private ErrorCode           status;
    private SupportedLeFeatures leFeatures;
}
