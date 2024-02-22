package fr.gette.hciexplorer.hciSpecification.event.commandComplete;

import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReadInquiryResponseTransmitPowerLevelComplete extends EventCommandComplete {
    private CommandCode commandOpCode = CommandCode.READ_INQUIRY_RESPONSE_TRANSMIT_POWER_LEVEL;
    private ErrorCode status;
    private short txPower;
}
