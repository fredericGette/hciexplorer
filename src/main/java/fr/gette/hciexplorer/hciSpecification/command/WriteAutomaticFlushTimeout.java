package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.SimplePairingMode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WriteAutomaticFlushTimeout extends Command {
    private CommandCode opCode = CommandCode.WRITE_AUTOMATIC_FLUSH_TIMEOUT;
    private int connectionHandle;
    private int flushTimeout; // 0 = infinity; No Automatic Flush

    public double getFlushTimeoutInMs()
    {
        return flushTimeout * 0.625;
    }
}
