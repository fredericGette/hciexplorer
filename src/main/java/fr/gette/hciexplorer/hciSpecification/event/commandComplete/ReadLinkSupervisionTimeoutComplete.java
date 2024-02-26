package fr.gette.hciexplorer.hciSpecification.event.commandComplete;

import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReadLinkSupervisionTimeoutComplete extends EventCommandComplete {
    private CommandCode commandOpCode = CommandCode.READ_LINK_SUPERVISION_TIMEOUT;
    private ErrorCode status;
    private int handle;
    private int linkSupervisionTimeout;

    /**
     *
     * @return 0 means "no time out"
     */
    public Double getLinkSupervisionTimeoutInMs()
    {
        return this.linkSupervisionTimeout*0.625;
    }
}
