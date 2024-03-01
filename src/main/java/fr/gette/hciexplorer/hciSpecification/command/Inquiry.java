package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.LAP;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Inquiry extends Command {
    private CommandCode opCode = CommandCode.INQUIRY;
    private LAP         lap;
    private short inquiryLength;
    private short numResponses; // Maximum number of responses from the Inquiry before the Inquiry is halted (0x00 = unlimited).

    public double getInquiryLengthInSec()
    {
        return inquiryLength * 1.28;
    }
}
