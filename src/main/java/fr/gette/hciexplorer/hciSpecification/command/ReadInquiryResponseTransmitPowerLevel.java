package fr.gette.hciexplorer.hciSpecification.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReadInquiryResponseTransmitPowerLevel extends Command {
    private CommandCode opCode = CommandCode.READ_INQUIRY_RESPONSE_TRANSMIT_POWER_LEVEL;
}
