package fr.gette.hciexplorer.hciSpecification.event.commandComplete;

import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReadLocalVersionInformationComplete extends EventCommandComplete {
    private CommandCode commandOpCode = CommandCode.READ_LOCAL_VERSION_INFORMATION;
    private ErrorCode status;
    private short hciVersion;
    private int hciSubversion;
    private short lmpVersion;
    private int companyIdentifier;
    private int lmpSubversion;
}
