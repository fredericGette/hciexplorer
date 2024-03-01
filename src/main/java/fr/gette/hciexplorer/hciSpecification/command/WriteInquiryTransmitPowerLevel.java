package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.SimplePairingMode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WriteInquiryTransmitPowerLevel extends Command {
    private CommandCode opCode = CommandCode.WRITE_SIMPLE_PAIRING_MODE;
    private short TxPower;
}
