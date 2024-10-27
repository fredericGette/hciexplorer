package fr.gette.hciexplorer.hciSpecification.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WriteLinkSupervisionTimeout extends Command {
    private CommandCode opCode = CommandCode.WRITE_LINK_SUPERVISION_TIMEOUT;
    private int connectionHandle;
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
