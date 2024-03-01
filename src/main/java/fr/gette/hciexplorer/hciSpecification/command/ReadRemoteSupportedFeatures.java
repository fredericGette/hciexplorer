package fr.gette.hciexplorer.hciSpecification.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReadRemoteSupportedFeatures extends Command {
    private CommandCode opCode = CommandCode.READ_REMOTE_SUPPORTED_FEATURES;
    private int connectionHandle;
}
