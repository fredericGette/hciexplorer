package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.InquiryScanType;
import fr.gette.hciexplorer.hciSpecification.PageScanType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WriteInquiryScanType extends Command {
    private CommandCode opCode = CommandCode.WRITE_INQUIRY_SCAN_TYPE;
    private InquiryScanType scanType;
}
