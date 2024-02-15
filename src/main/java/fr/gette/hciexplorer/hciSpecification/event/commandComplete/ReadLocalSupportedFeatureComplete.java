package fr.gette.hciexplorer.hciSpecification.event.commandComplete;

import fr.gette.hciexplorer.hciSpecification.SupportedCommands;
import fr.gette.hciexplorer.hciSpecification.SupportedLmpFeatures;
import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReadLocalSupportedFeatureComplete extends EventCommandComplete {
    private CommandCode commandOpCode = CommandCode.READ_LOCAL_SUPPORTED_FEATURES;
    private ErrorCode status;
    private SupportedLmpFeatures lmpFeatures;
}
