package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.SimplePairingMode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WriteInquiryTransmitPowerLevel extends Command {
    private CommandCode opCode = CommandCode.WRITE_INQUIRY_TRANSMIT_POWER_LEVEL;
    private short TxPower;
}
