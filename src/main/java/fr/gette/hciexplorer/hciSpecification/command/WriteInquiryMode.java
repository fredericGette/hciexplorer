package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.InquiryMode;
import fr.gette.hciexplorer.hciSpecification.PageScanType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WriteInquiryMode extends Command {
    private CommandCode opCode = CommandCode.WRITE_INQUIRY_MODE;
    private InquiryMode inquiryMode;
}
