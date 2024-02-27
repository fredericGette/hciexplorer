package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.PageScanType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WritePageScanType extends Command {
    private CommandCode opCode = CommandCode.WRITE_PAGE_SCAN_TYPE;
    private PageScanType pageScanType;
}
