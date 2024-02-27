package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.SimplePairingMode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WritePageScanActivity extends Command {
    private CommandCode opCode = CommandCode.WRITE_PAGE_SCAN_ACTIVITY;
    private int pageScanInterval;
    private int pageScanWindow;

    public Double getPageScanIntervalInMs()
    {
        return pageScanInterval * 0.625;
    }

    public Double getPageScanWindowInMs()
    {
        return pageScanWindow * 0.625;
    }
}
