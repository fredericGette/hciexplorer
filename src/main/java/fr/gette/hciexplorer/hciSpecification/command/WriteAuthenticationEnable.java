package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.AuthenticationEnable;
import fr.gette.hciexplorer.hciSpecification.SimplePairingMode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WriteAuthenticationEnable extends Command {
    private CommandCode opCode = CommandCode.WRITE_AUTHENTICATION_ENABLE;
    private AuthenticationEnable authenticationEnable ;
}
