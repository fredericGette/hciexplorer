package fr.gette.hciexplorer.hciSpecification.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationRequested extends Command {
    private CommandCode opCode = CommandCode.AUTHENTICATION_REQUESTED;
    private int connectionHandle;
}
