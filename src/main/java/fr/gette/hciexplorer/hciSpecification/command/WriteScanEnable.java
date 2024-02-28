package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.InquiryMode;
import fr.gette.hciexplorer.hciSpecification.ScanEnable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WriteScanEnable extends Command {
    private CommandCode opCode = CommandCode.WRITE_SCAN_ENABLE;
    private ScanEnable scanEnable;
}
