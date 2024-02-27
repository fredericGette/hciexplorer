package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.SimplePairingMode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WritePageTimeout extends Command {
    private CommandCode opCode = CommandCode.WRITE_PAGE_TIMEOUT;
    private int pageTimeout;

    public Double getPageTimeoutInMs()
    {
        return pageTimeout * 0.625;
    }
}
