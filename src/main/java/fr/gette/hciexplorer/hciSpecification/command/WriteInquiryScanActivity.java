package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.SimplePairingMode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WriteInquiryScanActivity extends Command {
    private CommandCode opCode = CommandCode.WRITE_INQUIRY_SCAN_ACTIVITY;
    private int inquiryScanInterval;
    private int inquiryScanWindow;

    public Double getInquiryScanIntervalInMs()
    {
        return inquiryScanInterval * 0.625;
    }

    public Double getInquiryScanWindowInMs()
    {
        return inquiryScanWindow * 0.625;
    }

}
