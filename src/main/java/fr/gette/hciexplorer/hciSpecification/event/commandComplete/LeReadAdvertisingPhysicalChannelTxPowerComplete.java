package fr.gette.hciexplorer.hciSpecification.event.commandComplete;

import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LeReadAdvertisingPhysicalChannelTxPowerComplete extends EventCommandComplete {
    private CommandCode commandOpCode = CommandCode.LE_READ_ADVERTISING_PHYSICAL_CHANNEL_TX_POWER;
    private ErrorCode status;
    private short transmitPowerLevel;
}
