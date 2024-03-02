package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.EncryptionEnabled;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SetConnectionEncryption extends Command {
    private CommandCode opCode = CommandCode.SET_CONNECTION_ENCRYPTION;
    private int connectionHandle;
    private EncryptionEnabled encryptionEnable;
}
