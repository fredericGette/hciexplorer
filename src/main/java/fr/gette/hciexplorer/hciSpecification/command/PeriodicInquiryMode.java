package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.LAP;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PeriodicInquiryMode extends Command {
    private CommandCode opCode = CommandCode.PERIODIC_INQUIRY_MODE;
    private int maxPeriodLength;
    private int minPeriodLength;
    private LAP         lap;
    private short inquiryLength;
    private short numResponses; // Maximum number of responses from the Inquiry before the Inquiry is halted (0x00 = unlimited).

    public double getInquiryLengthInSec()
    {
        return inquiryLength * 1.28;
    }
    public double getMaxPeriodLengthInSec()
    {
        return maxPeriodLength * 1.28;
    }
    public double getMinPeriodLengthInSec()
    {
        return minPeriodLength * 1.28;
    }
}
