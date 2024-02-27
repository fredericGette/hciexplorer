package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.ExtendedInquiryResponse;
import fr.gette.hciexplorer.hciSpecification.FecRequired;
import fr.gette.hciexplorer.hciSpecification.SimplePairingMode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WriteExtendedInquiryResponse extends Command {
    private CommandCode opCode = CommandCode.WRITE_EXTENDED_INQUIRY_RESPONSE;
    private FecRequired fecRequired;
    private ExtendedInquiryResponse extendedInquiryResponse;
}
